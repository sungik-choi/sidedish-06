package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.dao.DetailDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DetailController {

    private final DetailDao detailDao;

    @GetMapping("/detail/{hash}")
    public Object reade(@PathVariable String hash) {
        return detailDao.read(hash);
    }
}
