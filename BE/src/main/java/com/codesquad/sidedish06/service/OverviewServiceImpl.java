package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.MenuTypeDao;
import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.dto.RequestOverview;
import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.codesquad.sidedish06.utils.JsonUtils.data;
import static com.codesquad.sidedish06.utils.UrlUtils.BASE_URL;

@RequiredArgsConstructor
@Service
public class OverviewServiceImpl implements OverviewService {

    private final MenuTypeDao menuTypeDao;

    private final OverviewDao overviewDao;

    @Override
    public void save() throws IOException, URISyntaxException {

        List<String> menus = menuTypeDao.listMenu();

        for (String menu : menus) {
            RequestOverview[] overviews = listOverview("/" + menu);

            for (int i = 0; i < overviews.length; i++) {
                if (overviews[i] != null) {
                    validate(overviews[i]);
                    overviewDao.insert(overviews[i], menu);
                }
            }
        }
    }

    @Override
    public ResponseOverview listMenu(String menu) {
        boolean isNotExist = menuTypeDao.listMenu().contains(menu);

        if (!isNotExist) {
            throw new IllegalArgumentException();
        }

        return overviewDao.listOverview(menu);
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
