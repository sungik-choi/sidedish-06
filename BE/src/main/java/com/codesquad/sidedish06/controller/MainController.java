package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.ShortenDao;
import com.codesquad.sidedish06.domain.dto.Shorten;
import com.codesquad.sidedish06.service.ShortenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class MainController {

    private final ShortenService shortenService;

    private final ShortenDao shortenDao;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public Shorten[] test(String menu) throws URISyntaxException, IOException {
        return shortenService.saveShortenInformation(menu);
    }

    @GetMapping("/test")
    public Object test2() throws IOException, URISyntaxException {
        Shorten[] shortens = test("/main");
        for (Shorten shorten : shortens) {
            shortenDao.insert(shorten);
        }
        return shortens;
    }
}
