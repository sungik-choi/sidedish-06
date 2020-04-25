package com.codesquad.sidedish06.utils;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperUtils {

    private RowMapperUtils() {}

    public static RowMapper<String> getFirstColumns() {
        return new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
    }
}