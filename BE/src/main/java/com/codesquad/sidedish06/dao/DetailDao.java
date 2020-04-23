package com.codesquad.sidedish06.dao;

import com.codesquad.sidedish06.domain.dto.RequestDetail;
import com.codesquad.sidedish06.domain.dto.ResponseDetail;
import com.codesquad.sidedish06.domain.entity.DetailSection;
import com.codesquad.sidedish06.domain.entity.ThumbImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class DetailDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DetailDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(RequestDetail detail) {

        if (detail == null) {
            return;
        }

        String sql = "update babchan set top_image = ?, point = ?, delivery_info = ?, delivery_fee = ? where hash = ?";

        this.jdbcTemplate.update(sql,
                detail.getTop_image(),
                detail.getPoint(),
                detail.getDelivery_info(),
                detail.getDelivery_fee(),
                detail.getHash()
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

    public ResponseDetail read(String hash) {
        String sql = "select * from babchan where hash = ?";

        RowMapper<ResponseDetail> responseDetailRowMapper = new RowMapper<ResponseDetail>() {
            @Override
            public ResponseDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResponseDetail response = new ResponseDetail();
                response.setHash(hash);
                response.setTitle(rs.getString("title"));
                response.setTop_image(rs.getString("image"));
                response.setThumb_images(thumbImages(hash));
                response.setDescription(rs.getString("description"));
                response.setPoint(rs.getString("point"));
                response.setDelivery_info(rs.getString("delivery_info"));
                response.setDelivery_fee(rs.getString("delivery_fee"));
                response.setOriginPrice(rs.getString("n_price"));
                response.setSalePrice(rs.getString("s_price"));
                response.setDetail_section(sections(hash));
                return response;
            }
        };

        return this.jdbcTemplate.queryForObject(sql, new Object[]{hash}, responseDetailRowMapper);
    }

    private List<String> thumbImages(String hash) {
        String sql = "select imageUrl from thumb_image where hash = ?";

        RowMapper<ThumbImage> thumbImageRowMapper = new RowMapper<ThumbImage>() {
            @Override
            public ThumbImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                ThumbImage thumbImage = new ThumbImage();
                thumbImage.setImageUrl(rs.getString("imageUrl"));
                return thumbImage;
            }
        };

        List<ThumbImage> thumbImageList = this.jdbcTemplate.query(sql, new Object[]{hash}, thumbImageRowMapper);

        List<String> imageUrls = new ArrayList<>();

        for (ThumbImage image : thumbImageList) {
            imageUrls.add(image.getImageUrl());
        }

        return imageUrls;
    }

    private List<String> sections(String hash) {
        String sql = "select imageUrl from detail_section where hash = ?";

        RowMapper<DetailSection> detailSectionRowMapper = new RowMapper<DetailSection>() {
            @Override
            public DetailSection mapRow(ResultSet rs, int rowNum) throws SQLException {
                DetailSection detailSection = new DetailSection();
                detailSection.setImageUrl(rs.getString("imageUrl"));
                return detailSection;
            }
        };

        List<DetailSection> sectionList = this.jdbcTemplate.query(sql, new Object[]{hash}, detailSectionRowMapper);

        List<String> sections = new ArrayList<>();

        for (DetailSection section : sectionList) {
            sections.add(section.getImageUrl());
        }

        return sections;
    }
}
