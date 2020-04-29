package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.domain.dto.ResponseDetail;
import com.codesquad.sidedish06.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DetailController {

    private final DetailService detailService;

    @GetMapping("/detail/{hash}")
    public ResponseDetail read(@PathVariable String hash) {
        return detailService.read(hash);
    }
}
