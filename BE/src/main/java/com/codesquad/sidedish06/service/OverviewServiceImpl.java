package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.codesquad.sidedish06.utils.JsonUtils.BASE_URL;
import static com.codesquad.sidedish06.utils.JsonUtils.data;

@RequiredArgsConstructor
@Service
public class OverviewServiceImpl implements OverviewService {

    private final OverviewDao overviewDao;

    @Override
    public Object save() throws IOException, URISyntaxException {
        List<String> menus = overviewDao.listType();

        for (String menu : menus) {
            RequestOverview[] overviews = listOverview("/" + menu);

            for (int i = 0; i < overviews.length; i++) {
                if (overviews[i] != null) {
                    validate(overviews[i]);
                    overviewDao.insert(overviews[i], menu);
                }
            }
        }
        return "OK";
    }

    @Override
    public ResponseOverview listMenu(String menu) {
        return overviewDao.listMenu(menu);
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

    private RequestOverview[] listOverview(String menu) throws URISyntaxException, IOException {
        String url = BASE_URL + menu;
        String data = data(url);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonNode = objectMapper.readValue(data, JsonNode.class).get("body");

        return objectMapper.convertValue(jsonNode, RequestOverview[].class);
    }
}
