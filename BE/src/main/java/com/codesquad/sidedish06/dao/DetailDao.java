package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.entity.Badge;
import com.codesquad.sidedish06.domain.entity.Delivery;
import com.codesquad.sidedish06.domain.entity.Detail;
import com.codesquad.sidedish06.domain.entity.Overview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DetailDao {

    private final JdbcTemplate jdbcTemplate;

    private final Logger logger = LoggerFactory.getLogger(OverviewDao.class);

    @Autowired
    public DetailDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Detail insert(Overview overview) {

        String sql = "insert into overview(detail_hash, image, alt, title, description, n_price, s_price)" +
                "values (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, overview.getDetail_hash(),
                overview.getImage(),
                overview.getAlt(),
                overview.getTitle(),
                overview.getDescription(),
                overview.getN_price(),
                overview.getS_price()
        );

        sql = "insert into delivery(detail_hash, type) VALUES (?, ?)";

        if (overview.getDelivery_type() == null) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), null);
        } else {
            for (Delivery delivery : overview.getDelivery_type()) {
                jdbcTemplate.update(sql, overview.getDetail_hash(), delivery.getType());
            }
        }

        sql = "insert into badge(detail_hash, event) VALUES (?, ?)";

        if (overview.getBadge() == null) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), null);
        } else {
            for (Badge badge : overview.getBadge()) {
                jdbcTemplate.update(sql, overview.getDetail_hash(), badge.getEvent());
            }
        }

        return overview;
    }
}
