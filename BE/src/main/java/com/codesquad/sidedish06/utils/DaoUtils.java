package com.codesquad.sidedish06.utils;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DaoUtils {

    public static Map<String, String[]> menuInfo;

    private DaoUtils() {}

    public static void setTitles() {
        DaoUtils.menuInfo = new HashMap<>();
        DaoUtils.menuInfo.put("main", new String[]{"밥과 함께", "언제 먹어도 든든한 반찬"});
        DaoUtils.menuInfo.put("soup", new String[]{"국, 찌개", "김이 모락모락 국, 찌개"});
        DaoUtils.menuInfo.put("side", new String[]{"밑반찬", "언제 먹어도 든든한 밑반찬"});
    }

    public static RowMapper<String> getFirstColumns() {
        return new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        };
    }
}