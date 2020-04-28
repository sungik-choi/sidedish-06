package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.MenuTypeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UrlController {

    private final MenuTypeDao menuTypeDao;

    @GetMapping("/babchan-url")
    public List<String> urls() {
        return menuTypeDao.listMenuUrl();
    }
}
