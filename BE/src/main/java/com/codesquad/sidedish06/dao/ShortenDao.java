package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.Badge;
import com.codesquad.sidedish06.domain.dto.Delivery;
import com.codesquad.sidedish06.domain.dto.Shorten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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

        sql = "insert into delivery(detail_hash, DELIVERY_TYPE) VALUES (?, ?)";

        if (shorten.getDelivery_type() == null) {
            jdbcTemplate.update(sql, shorten.getDetail_hash(), null);
        } else {
            for (Delivery delivery : shorten.getDelivery_type()) {
                jdbcTemplate.update(sql, shorten.getDetail_hash(), delivery.getDelivery_type());
            }
        }

        sql = "insert into badge(detail_hash, badge) VALUES (?, ?)";

        if (shorten.getBadge() == null) {
            jdbcTemplate.update(sql, shorten.getDetail_hash(), null);
        } else {
            for (Badge badge : shorten.getBadge()) {
                jdbcTemplate.update(sql, shorten.getDetail_hash(), badge.getBadge());
            }
        }

        return shorten;
    }
}
