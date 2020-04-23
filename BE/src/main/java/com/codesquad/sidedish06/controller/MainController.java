package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final OverviewDao overviewDao;

    @GetMapping("/babchan-url")
    public List<String> urls() {
        return UrlUtils.urls();
    }

    @GetMapping("/test")
    public Object test() {
        return overviewDao.deliveries("H077F");
    }
}
