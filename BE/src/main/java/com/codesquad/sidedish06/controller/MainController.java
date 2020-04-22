package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.DetailDao;
import com.codesquad.sidedish06.service.DetailService;
import com.codesquad.sidedish06.service.OverviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final OverviewService overviewService;

    private final DetailService detailService;

//    @GetMapping("/overview/dblist")
//    public Object listDbOverview() {
//        return overviewService.listDbOverview("main");
//    }

    @GetMapping("/{menu}")
    public Object saveOverview(@PathVariable String menu) throws IOException, URISyntaxException {
        return overviewService.create(menu);
    }

    @GetMapping("/detail/create")
    public Object saveDetail() throws URISyntaxException, JsonProcessingException {
        return detailService.create();
    }

//    @GetMapping("/detail/mockup")
//    public Object detail() throws URISyntaxException, JsonProcessingException {
//        Detail[] details = listDetail();
//        for (Detail detail : details) {
//            detailDao.insert(detail);
//        }
//        return details;
//    }
}
