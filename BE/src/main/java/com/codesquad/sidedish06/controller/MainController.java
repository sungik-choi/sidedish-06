package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.ShortenDao;
import com.codesquad.sidedish06.domain.entity.Shorten;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.codesquad.sidedish06.utils.JsonUtils.listShorten;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final ShortenDao shortenDao;

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public Object test() {
        return shortenDao.listShorten();
    }

    @GetMapping("/test")
    public Object test2() throws IOException, URISyntaxException {
        Shorten[] shortens = listShorten("/main/");
        for (Shorten shorten : shortens) {
            shortenDao.insert(shorten);
        }
        return shortens;
    }
}
