package com.codesquad.sidedish06.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.codesquad.sidedish06.utils.JsonUtils.BASE_URL;

@Slf4j
@Repository
public class MenuTypeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MenuTypeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> listMenuUrl() {
        String sql = "select distinct type from food_type";

        RowMapper<String> rowMapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return BASE_URL + "/" + rs.getString(1);
            }
        };

        return this.jdbcTemplate.query(sql, rowMapper);
    }
}
