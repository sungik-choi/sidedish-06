package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.DetailDao;
import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.entity.Detail;
import com.codesquad.sidedish06.domain.entity.Overview;
import com.codesquad.sidedish06.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final OverviewDao overviewDao;

    private final DetailDao detailDao;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/overview/dblist")
    public Object test() {
        return overviewDao.listOverview();
    }

    @GetMapping("/overview/mockup")
    public Object test2() throws IOException, URISyntaxException {
        Overview[] overviews = JsonUtils.listOverview("/main/");
        for (Overview overview : overviews) {
            overviewDao.insert(overview);
        }
        return overviews;
    }

//    @GetMapping("/detail/dblist")
//    public Object listDetail() {
//        return detailDao.listOverview();
//    }

    @GetMapping("/detail/mockup")
    public Object detail() throws URISyntaxException, JsonProcessingException {
        Detail[] details = JsonUtils.listDetail();
        return details;
    }
}
