package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.OverviewDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OverviewController {

    private final OverviewDao overviewDao;

    @GetMapping("/overview/{menu}")
    public Object list(@PathVariable String menu) {
        return overviewDao.listMenuOverview(menu);
    }
}
