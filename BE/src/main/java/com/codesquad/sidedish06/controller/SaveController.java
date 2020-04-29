package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.service.DetailService;
import com.codesquad.sidedish06.service.OverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class SaveController {
        //데이터베이스 저장용컨트롤러 입니다.
//    private final OverviewService overviewService;
//
//    private final DetailService detailService;
//
//    @GetMapping("/save")
//    public HttpStatus saveOverview() throws IOException, URISyntaxException {
//        overviewService.save();
//        detailService.save();
//        return HttpStatus.OK;
//    }
}
