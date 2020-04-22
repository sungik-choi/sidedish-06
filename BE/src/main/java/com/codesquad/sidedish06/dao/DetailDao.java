package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.entity.Detail;
import com.codesquad.sidedish06.domain.entity.DetailSection;
import com.codesquad.sidedish06.domain.entity.Price;
import com.codesquad.sidedish06.domain.entity.ThumbImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public Detail insert(Detail detail) {

        String sql = "insert into detail(detail_hash, top_image, product_description, point, delivery_info, delivery_fee)" +
                "values (null, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, detail.getTop_image(),
                detail.getProduct_description(),
                detail.getPoint(),
                detail.getDelivery_info(),
                detail.getDelivery_fee()
        );

        sql = "insert into detail_section(detail_hash, image_url) VALUES (null, ?)";

        if (detail.getDetail_section() == null) {
            jdbcTemplate.update(sql, detail.getDetail_section(), null);
        } else {
            for (DetailSection detailSection : detail.getDetail_section()) {
                jdbcTemplate.update(sql, null, detailSection.getImageUrl());
            }
        }

        sql = "insert into price(detail_hash, price) VALUES (null, ?)";

        if (detail.getPrices() == null) {
            jdbcTemplate.update(sql, detail.getPrices(), null);
        } else {
            for (Price price : detail.getPrices()) {
                jdbcTemplate.update(sql, null, price.getPrice());
            }
        }

        sql = "insert into thumb_image(detail_hash, image_url) VALUES (null, ?)";

        if (detail.getThumb_images() == null) {
            jdbcTemplate.update(sql, detail.getThumb_images(), null);
        } else {
            for (ThumbImage thumbImage : detail.getThumb_images()) {
                jdbcTemplate.update(sql, null, thumbImage.getImageUrl());
            }
        }

        return detail;
    }

//    public List<ResponseDetail> listOverview() {
//        String sql = "select * from detail";
//
//        RowMapper<ResponseDetail> responseDetailRowMapper = new RowMapper<ResponseDetail>() {
//            @Override
//            public ResponseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ResponseDetail response = new ResponseDetail();
//                response.setDetail_hash(rs.getString("detail_hash"));
//                response.setImage(rs.getString("image"));
//                response.setAlt(rs.getString("alt"));
//                response.setDelivery_type(deliveries(response));
//                response.setTitle(rs.getString("title"));
//                response.setDescription(rs.getString("description"));
//                response.setN_price(rs.getString("n_price"));
//                response.setS_price(rs.getString("s_price"));
//                response.setBadge(badges(response));
//                return response;
//            }
//        };
//
//        return this.jdbcTemplate.query(sql, responseOverviewRowMapper);
//    }
}
