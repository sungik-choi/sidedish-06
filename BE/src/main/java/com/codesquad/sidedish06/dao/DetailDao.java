package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestDetailDTO;
import com.codesquad.sidedish06.domain.entity.DetailSection;
import com.codesquad.sidedish06.domain.entity.ThumbImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository
public class DetailDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DetailDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(RequestDetailDTO detail) {

        if (detail == null) {
            return;
        }

        String sql = "update banchan set top_image = ?, point = ?, delivery_info = ?, delivery_fee = ? where title = ?";

        log.info("detail : {}", detail);

        this.jdbcTemplate.update(sql,
                detail.getTop_image(),
                detail.getPoint(),
                detail.getDelivery_info(),
                detail.getDelivery_fee(),
                detail.getProduct_description()
        );

        sql = "insert into thumb_image(hash, imageUrl) VALUES (?, ?)";

        if (detail.getThumb_images() == null) {
            jdbcTemplate.update(sql, detail.getHash(), null);
        } else {
            for (ThumbImage thumbImage : detail.getThumb_images()) {
                jdbcTemplate.update(sql, detail.getHash(), thumbImage.getImageUrl());
            }
        }

        sql = "insert into detail_section(hash, imageUrl) VALUES (?, ?)";

        if (detail.getDetail_section() == null) {
            jdbcTemplate.update(sql, detail.getHash(), null);
        } else {
            for (DetailSection detailSection : detail.getDetail_section()) {
                jdbcTemplate.update(sql, detail.getHash(), detailSection.getImageUrl());
            }
        }
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
