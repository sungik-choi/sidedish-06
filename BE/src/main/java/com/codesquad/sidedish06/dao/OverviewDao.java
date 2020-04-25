package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import com.codesquad.sidedish06.utils.RowMapperUtils;
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

    private Map<String, String[]> menuInfo;

    @Autowired
    public OverviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private void setTitles() {
        this.menuInfo = new HashMap<>();
        this.menuInfo.put("main", new String[]{"밥과 함께", "언제 먹어도 든든한 반찬"});
        this.menuInfo.put("soup", new String[]{"국, 찌개", "김이 모락모락 국, 찌개"});
        this.menuInfo.put("side", new String[]{"밑반찬", "언제 먹어도 든든한 밑반찬"});
    }

    public void insert(RequestOverview overview, String menu) {
        setTitles();
        validate(overview);
        if (isNotDuplicatedHash(overview)) {
            insertOverview(overview);
            insertFoodType(overview, menu);
            insertDelivery(overview);
            insertBadge(overview);
        }
    }

    private void validate(RequestOverview overview) {
        if (overview.getN_price() != null) {
            overview.setN_price(overview.getN_price() + "원");
        }

        if (overview.getDelivery_type() == null) {
            overview.setDelivery_type(new ArrayList<>());
        }

        if (overview.getBadge() == null) {
            overview.setBadge(new ArrayList<>());
        }
    }

    private boolean isNotDuplicatedHash(RequestOverview overview) {
        String sql = "select count(*) from babchan where hash = ?";

        return this.jdbcTemplate.queryForObject(sql, new Object[]{overview.getDetail_hash()}, Integer.class) == 0;
    }

    private void insertOverview(RequestOverview overview) {

        String sql = "insert into babchan (hash, image, alt, title, description, n_price, s_price)" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                overview.getDetail_hash(),
                overview.getImage(),
                overview.getAlt(),
                overview.getTitle(),
                overview.getDescription(),
                overview.getN_price(),
                overview.getS_price()
        );
    }

    private void insertFoodType(RequestOverview overview, String menu) {
        String[] titles = menuInfo.get(menu);
        String subTitle = titles[0];
        String mainTitle = titles[1];

        String sql = "insert into food_type(hash, type, sub_title, main_title) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, overview.getDetail_hash(), menu, subTitle, mainTitle);
    }

    private void insertDelivery(RequestOverview overview) {
        String sql = "insert into delivery(hash, type) VALUES (?, ?)";

        for (Delivery delivery : overview.getDelivery_type()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), delivery.getType());
        }
    }

    private void insertBadge(RequestOverview overview) {
        String sql = "insert into badge(hash, event) VALUES (?, ?)";

        for (Badge badge : overview.getBadge()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), badge.getEvent());
        }
    }

    public ResponseOverview listMenuOverview(String menu) {
        String sql = "select distinct sub_title, main_title from food_type where type = ?";

        RowMapper<List<String>> rowMapper = new RowMapper<List<String>>() {
            @Override
            public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                List<String> titles = new ArrayList<>();
                titles.add(rs.getString("sub_title"));
                titles.add(rs.getString("main_title"));
                return titles;
            }
        };

        List<String> titles = this.jdbcTemplate.queryForObject(sql, new Object[]{menu}, rowMapper);

        String subtitle = titles.get(0);
        String mainTitle = titles.get(1);

        return new ResponseOverview(
                subtitle,
                mainTitle,
                listMenuOverviewData(menu)
        );
    }

    public List<ResponseOverviewData> listMenuOverviewData(String menu) {
        String sql = "select * from babchan where hash in (select hash from food_type where type = ?)";

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
        String sql = "select type from delivery where hash = ?";

        return this.jdbcTemplate.query(sql, new Object[]{hash}, RowMapperUtils.getFirstColumns());
    }

    private List<String> badges(String hash) {
        String sql = "select event from badge where hash = ?";

        return this.jdbcTemplate.query(sql, new Object[]{hash}, RowMapperUtils.getFirstColumns());
    }
}
