package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.service.OverviewService;
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

//    @GetMapping("/overview/dblist")
//    public Object listDbOverview() {
//        return overviewDao.listOverview();
//    }

//    @GetMapping("/{menu}")
//    public Object saveOverview(@PathVariable String menu) throws IOException, URISyntaxException {
//        return overviewService.list(menu);
//    }

//    @GetMapping("/detail/dblist")
//    public Object listDetail() {
//        return detailDao.listOverview();
//    }

//    @GetMapping("/detail/mockup")
//    public Object detail() throws URISyntaxException, JsonProcessingException {
//        Detail[] details = listDetail();
//        for (Detail detail : details) {
//            detailDao.insert(detail);
//        }
//        return details;
//    }
}
