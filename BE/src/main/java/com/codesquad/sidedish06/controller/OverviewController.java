package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.domain.dto.ResponseOverview;
import com.codesquad.sidedish06.service.OverviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OverviewController {

    private final OverviewService overviewService;

    @GetMapping("/{menu}")
    public ResponseOverview list(@PathVariable String menu) {
        return overviewService.listMenu(menu);
    }
}
