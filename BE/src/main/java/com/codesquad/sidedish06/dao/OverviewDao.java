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
import javax.validation.Valid;
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

    public void insert(@Valid RequestOverview overview, String menu) {
        if (isNotDuplicatedHash(overview)) {
            insertOverview(overview, menu);
            insertDelivery(overview);
            insertBadge(overview);
        }
    }

    private boolean isNotDuplicatedHash(RequestOverview overview) {
        String sql = "select count(*) from babchan where hash = ?";
        int count = this.jdbcTemplate.queryForObject(sql, Integer.class, overview.getDetail_hash());
        return count == 0;
    }

    private void insertOverview(RequestOverview overview, String menu) {

        String sql = "insert into babchan (hash, food_type, image, alt, title, description, n_price, s_price)" +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";

        String normalPrice = overview.getN_price();

        if (normalPrice != null) {
            normalPrice += "원";
        }

        jdbcTemplate.update(sql,
                overview.getDetail_hash(),
                menu,
                overview.getImage(),
                overview.getAlt(),
                overview.getTitle(),
                overview.getDescription(),
                normalPrice,
                overview.getS_price()
        );
    }

    private void insertDelivery(RequestOverview overview) {
        String sql = "insert into delivery(hash, type) VALUES (?, ?)";

        if (overview.getDelivery_type() == null) {
            return;
        }

        for (Delivery delivery : overview.getDelivery_type()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), delivery.getType());
        }
    }

    private void insertBadge(RequestOverview overview) {
        String sql = "insert into badge(hash, event) VALUES (?, ?)";

        if (overview.getBadge() == null) {
            return;
        }

        for (Badge badge : overview.getBadge()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), badge.getEvent());
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

    public List<String> deliveries(String hash) {
        return convertListObject2ListString("type","delivery", hash);
    }

    private List<String> badges(String hash) {
        return convertListObject2ListString("event", "badge", hash);
    }

    private List<String> convertListObject2ListString(String field, String className, String hash) {
        String sql = "select " + field + " from " + className + " where hash = ?";

        RowMapper<String> badgeRowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{hash}, badgeRowMapper);
    }
}
