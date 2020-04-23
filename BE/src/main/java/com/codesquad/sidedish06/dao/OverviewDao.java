package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class OverviewDao {

    private final JdbcTemplate jdbcTemplate;

    private final Map<String, String[]> menuInfo;

    @Autowired
    public OverviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.menuInfo = new HashMap<>();

        this.menuInfo.put("main", new String[]{"밥과 함께", "언제먹어도 든든한 반찬"});
        this.menuInfo.put("soup", new String[]{"국, 찌개", "김이 모락모락 국, 찌개"});
        this.menuInfo.put("side", new String[]{"밑반찬", "언제먹어도 든든한 밑반찬"});
    }

    public void insert(RequestOverview overview, String menu) {

        String sql = "insert into babchan (hash, food_type, image, alt, title, description, n_price, s_price)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";

        String normalPrice = overview.getN_price();

        if(normalPrice!=null) {
            normalPrice += "원";
        }

        jdbcTemplate.update(sql,
                overview.getDetail_hash(),
                menu,
                overview.getImage(),
                overview.getAlt(),
                overview.getTitle(),
                overview.getDescription(),
                overview.getN_price(),
                overview.getS_price()
        );

        sql = "insert into delivery(hash, type) VALUES (?, ?)";

        if (overview.getDelivery_type() == null) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), null);
        } else {
            for (Delivery delivery : overview.getDelivery_type()) {
                jdbcTemplate.update(sql, overview.getDetail_hash(), delivery.getType());
            }
        }

        sql = "insert into badge(hash, event) VALUES (?, ?)";

        if (overview.getBadge() == null) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), null);
        } else {
            for (Badge badge : overview.getBadge()) {
                jdbcTemplate.update(sql, overview.getDetail_hash(), badge.getEvent());
            }
        }
    }

    public ResponseOverview listMenuOverview(String menu) {
        String[] informations = menuInfo.get(menu);

        return new ResponseOverview(
                informations[0],
                informations[1],
                listMenuOverviewData(menu)
        );
    }

    public List<ResponseOverviewData> listMenuOverviewData(String menu) {
        String sql = "select * from babchan where food_type = ?";

        RowMapper<ResponseOverviewData> responseOverviewRowMapper = new RowMapper<ResponseOverviewData>() {
            @Override
            public ResponseOverviewData mapRow(ResultSet rs, int rowNum) throws SQLException {

                String hash = rs.getString("hash");

                ResponseOverviewData data = new ResponseOverviewData();
                data.setHash(hash);
                data.setImage(rs.getString("image"));
                data.setAlt(rs.getString("alt"));
                data.setDelivery_type(deliveries(hash));
                data.setTitle(rs.getString("title"));
                data.setDescription(rs.getString("description"));
                data.setOriginPrice(rs.getString("n_price"));
                data.setSalePrice(rs.getString("s_price"));
                data.setBadge(badges(hash));
                return data;
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{menu}, responseOverviewRowMapper);
    }

    private List<String> deliveries(String hash) {
        String sql = "select type from delivery where hash = ?";

        RowMapper<Delivery> deliveryRowMapper = new RowMapper<Delivery>() {
            @Override
            public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {
                Delivery delivery = new Delivery();
                delivery.setType(rs.getString("type"));
                return delivery;
            }
        };

        List<Delivery> deliveries = this.jdbcTemplate.query(sql, new Object[]{hash}, deliveryRowMapper);

        List<String> types = new ArrayList<>();

        for (Delivery delivery : deliveries) {
            types.add(delivery.getType());
        }

        return types;
    }

    private List<String> badges(String hash) {
        String sql = "select event from badge where hash = ?";

        RowMapper<Badge> badgeRowMapper = new RowMapper<Badge>() {
            @Override
            public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
                Badge badge = new Badge();
                badge.setEvent(rs.getString("event"));
                return badge;
            }
        };

        List<Badge> badges = this.jdbcTemplate.query(sql, new Object[]{hash}, badgeRowMapper);

        List<String> events = new ArrayList<>();

        for (Badge badge : badges) {
            events.add(badge.getEvent());
        }

        return events;
    }
}
