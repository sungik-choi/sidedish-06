package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseBadge;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverviewData;
import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codesquad.sidedish06.utils.DaoUtils.getFirstColumns;

@Repository
public class OverviewDao {

    private final JdbcTemplate jdbcTemplate;

    private final Map<String, String> hexaMap;

    public OverviewDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.hexaMap = new HashMap<>();
    }

    private void setHexa() {
        this.hexaMap.put("이벤트특가", "#9676F7");
        this.hexaMap.put("론칭특가", "#E48276");
    }

    public void insert(RequestOverview overview, String menu) {
        setHexa();
        insertOverview(overview, menu);
        insertDelivery(overview);
        insertBadge(overview);
    }

    private void insertOverview(RequestOverview overview, String menu) {

        String sql = "INSERT INTO babchan (hash, type, image, alt, title, description, n_price, s_price)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
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
        } catch (DuplicateKeyException e) {
            dropAndCreateTable();

            insertOverview(overview, menu);
        }

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
            String badgeName = badge.getBadgeName();
            jdbcTemplate.update(sql, overview.getDetail_hash(), badgeName, this.hexaMap.get(badgeName));
        }
    }

    public ResponseOverview listOverview(String menu) {
        String sql = "select menu_index, sub_title, main_title from food_type where type = ?";

        RowMapper<ResponseOverview> rowMapper = new RowMapper<ResponseOverview>() {
            @Override
            public ResponseOverview mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResponseOverview overview = new ResponseOverview();
                overview.setMenuIndex(rs.getInt("menu_index"));
                overview.setMenuType(rs.getString("sub_title"));
                overview.setMenuTypeTitle(rs.getString("main_title"));
                overview.setData(listMenuOverviewData(menu));
                return overview;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{menu}, rowMapper);
    }

    public List<ResponseOverviewData> listMenuOverviewData(String menu) {
        String sql = "SELECT hash, image, alt, title, description, n_price, s_price " +
                "FROM babchan WHERE type = ?";

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

    private List<ResponseBadge> badges(String hash) {
        String sql = "select badgeName, badgeHexa from badge where hash = ?";

        RowMapper<ResponseBadge> badgeRowMapper = new RowMapper<ResponseBadge>() {
            @Override
            public ResponseBadge mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResponseBadge badge = new ResponseBadge();
                badge.setBadgeName(rs.getString("badgeName"));
                badge.setBadgeHexa(rs.getString("badgeHexa"));
                return badge;
            }
        };

        return this.jdbcTemplate.query(sql, new Object[]{hash}, badgeRowMapper);
    }

    private void dropAndCreateTable() {
        String dropSql = "DROP TABLE babchan";

        this.jdbcTemplate.execute(dropSql);

        String createSql = "CREATE TABLE babchan" +
                "(" +
                "hash VARCHAR(32) PRIMARY KEY," +
                "type VARCHAR(64) REFERENCES food_type (type) ON UPDATE CASCADE ON DELETE CASCADE," +
                "image VARCHAR(128)," +
                "alt VARCHAR(64)," +
                "title VARCHAR(128)," +
                "description VARCHAR(128)," +
                "n_price VARCHAR(32)," +
                "s_price VARCHAR(32)," +
                "top_image VARCHAR(128)," +
                "point VARCHAR(128)," +
                "delivery_info VARCHAR(128)," +
                "delivery_fee VARCHAR(128)" +
                ");";

        this.jdbcTemplate.execute(createSql);
    }
}
