package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.service.OverviewService;
import com.codesquad.sidedish06.utils.DaoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OverviewController {

    private final OverviewService overviewService;

    @GetMapping("/menu/{menu}")
    public ResponseOverview list(@PathVariable String menu) {
        if(!DaoUtils.menuInfo.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
        return overviewService.listMenu(menu);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequest() {
        return new ResponseEntity<>("Main, Soup, Side 중 1개를 입력해주세요!", HttpStatus.BAD_REQUEST);
    }
}
