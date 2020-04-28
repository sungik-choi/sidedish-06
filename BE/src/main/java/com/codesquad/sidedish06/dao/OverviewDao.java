package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import com.codesquad.sidedish06.utils.DaoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.codesquad.sidedish06.utils.DaoUtils.getFirstColumns;

@Slf4j
@Repository
public class OverviewDao {

    private final JdbcTemplate jdbcTemplate;

    public OverviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(RequestOverview overview, String menu) {
        if (isNotDuplicatedHash(overview)) {
            insertOverview(overview);
            insertFoodType(overview, menu);
            insertDelivery(overview);
            insertBadge(overview);
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
        String[] titles = DaoUtils.menuInfo.get(menu);
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
        String sql = "insert into badge(hash, badgeName, badgeHexa) VALUES (?, ?, ?)";

        for (Badge badge : overview.getBadge()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), badge.getBadgeName(), badge.getBadgeHexa());
        }
    }

    public ResponseOverview listTitles(String menu) {
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
        String sql = "SELECT bab.hash, image, alt, title, description, n_price, s_price " +
                "FROM babchan bab " +
                "JOIN food_type ft ON bab.hash = ft.hash " +
                "WHERE type = ?";

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

        return this.jdbcTemplate.query(sql, new Object[]{hash}, getFirstColumns());
    }

    private List<Badge> badges(String hash) {
        String sql = "select badgeName, badgeHexa from badge where hash = ?";

        RowMapper<Badge> badgeRowMapper = new RowMapper<Badge>() {
            @Override
            public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
                Badge badge = new Badge();
                badge.setBadgeName(rs.getString("badgeName"));
                badge.setBadgeHexa("badgeHexa");
                return badge;
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{hash}, badgeRowMapper);
    }
}
