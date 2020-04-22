package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.entity.Overview;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.codesquad.sidedish06.utils.JsonUtils.listOverview;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final OverviewDao overviewDao;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public Object test() {
        return overviewDao.listOverview();
    }

    @GetMapping("/test")
    public Object test2() throws IOException, URISyntaxException {
        Overview[] overviews = listOverview("/main/");
        for (Overview overview : overviews) {
            overviewDao.insert(overview);
        }
        return overviews;
    }
}
