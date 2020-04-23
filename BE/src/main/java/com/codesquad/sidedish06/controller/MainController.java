package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {

    @GetMapping("/babchan-url")
    public List<String> urls() {
        return UrlUtils.urls();
    }
}
