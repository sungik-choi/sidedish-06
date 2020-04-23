package com.codesquad.sidedish06.utils;

import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.entity.Delivery;

public class DaoUtils {
    public void insertDelivery(RequestOverview overview) {
        String sql = "insert into delivery(hash, type) VALUES (?, ?)";

        if (overview.getDelivery_type() == null) {
            return;
        }

        for (Delivery delivery : overview.getDelivery_type()) {
            jdbcTemplate.update(sql, overview.getDetail_hash(), delivery.getType());
        }
    }
}
