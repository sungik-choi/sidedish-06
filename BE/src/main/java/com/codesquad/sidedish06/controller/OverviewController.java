package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.service.OverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OverviewController {

    private final OverviewService overviewService;

    @GetMapping("/menu/{menu}")
    public ResponseOverview list(@PathVariable String menu) {
        return overviewService.listMenu(menu);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequest() {
        return new ResponseEntity<>("Main, Soup, Side 중 1개를 입력해주세요!", HttpStatus.BAD_REQUEST);
    }
}
