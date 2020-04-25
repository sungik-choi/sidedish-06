package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.dto.RequestOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.codesquad.sidedish06.utils.JsonUtils.listOverview;

@RequiredArgsConstructor
@Service
public class OverviewServiceImpl implements OverviewService {

    private final OverviewDao overviewDao;

    @Override
    public Object save() throws IOException, URISyntaxException {
        String[] menus = {"main", "soup", "side"};
        for (String menu : menus) {
            RequestOverview[] overviews = listOverview("/" + menu);
            for (int i = 0; i < overviews.length; i++) {
                if (overviews[i] == null) {
                    continue;
                }
                validate(overviews[i]);
                overviewDao.insert(overviews[i], menu);
            }
        }
        return "OK";
    }

    private void validate(RequestOverview overview) {
        if (overview.getN_price() != null) {
            overview.setN_price(overview.getN_price() + "원");
        }

        if (overview.getDelivery_type() == null) {
            overview.setDelivery_type(new ArrayList<>());
        }

        if (overview.getBadge() == null) {
            overview.setBadge(new ArrayList<>());
        }
    }
}
