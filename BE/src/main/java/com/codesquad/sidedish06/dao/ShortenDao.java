package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.ResponseShorten;
import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import com.codesquad.sidedish06.domain.entity.Shorten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShortenDao {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(ShortenDao.class);

    @Autowired
    public ShortenDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Shorten insert(Shorten shorten) {

        String sql = "insert into shorten(detail_hash, image, alt, title, description, n_price, s_price)" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, shorten.getDetail_hash(),
                shorten.getImage(),
                shorten.getAlt(),
                shorten.getTitle(),
                shorten.getDescription(),
                shorten.getN_price(),
                shorten.getS_price()
        );

        sql = "insert into delivery(detail_hash, type) VALUES (?, ?)";

        if (shorten.getDelivery_type() == null) {
            jdbcTemplate.update(sql, shorten.getDetail_hash(), null);
        } else {
            for (Delivery delivery : shorten.getDelivery_type()) {
                jdbcTemplate.update(sql, shorten.getDetail_hash(), delivery.getType());
            }
        }

        sql = "insert into badge(detail_hash, event) VALUES (?, ?)";

        if (shorten.getBadge() == null) {
            jdbcTemplate.update(sql, shorten.getDetail_hash(), null);
        } else {
            for (Badge badge : shorten.getBadge()) {
                jdbcTemplate.update(sql, shorten.getDetail_hash(), badge.getEvent());
            }
        }

        return shorten;
    }

    private List<String> deliveries(ResponseShorten response) {
        String sql = "select type from delivery where detail_hash = ?";

        RowMapper<Delivery> deliveryRowMapper = new RowMapper<Delivery>() {
            @Override
            public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {
                Delivery delivery = new Delivery();
                delivery.setType(rs.getString("type"));
                return delivery;
            }
        };

        List<Delivery> deliveries = this.jdbcTemplate.query(sql, new Object[]{response.getDetail_hash()}, deliveryRowMapper);

        List<String> types = new ArrayList<>();

        for (Delivery delivery : deliveries) {
            types.add(delivery.getType());
        }

        return types;
    }

    private List<String> badges(ResponseShorten response) {
        String sql = "select event from badge where detail_hash = ?";

        RowMapper<Badge> badgeRowMapper = new RowMapper<Badge>() {
            @Override
            public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
                Badge badge = new Badge();
                badge.setEvent(rs.getString("event"));
                return badge;
            }
        };

        List<Badge> badges = this.jdbcTemplate.query(sql, new Object[]{response.getDetail_hash()}, badgeRowMapper);

        List<String> events = new ArrayList<>();

        for (Badge badge : badges) {
            events.add(badge.getEvent());
        }

        return events;
    }

    public List<ResponseShorten> listShorten() {
        String sql = "select * from shorten";

        RowMapper<ResponseShorten> responseShortenRowMapper = new RowMapper<ResponseShorten>() {
            @Override
            public ResponseShorten mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResponseShorten response = new ResponseShorten();
                response.setDetail_hash(rs.getString("detail_hash"));
                response.setImage(rs.getString("image"));
                response.setAlt(rs.getString("alt"));
                response.setDelivery_type(deliveries(response));
                response.setTitle(rs.getString("title"));
                response.setDescription(rs.getString("description"));
                response.setN_price(rs.getString("n_price"));
                response.setS_price(rs.getString("s_price"));
                response.setBadge(badges(response));
                logger.info("response : {}", response);
                return response;
            }
        };

        return this.jdbcTemplate.query(sql, responseShortenRowMapper);
    }
}
