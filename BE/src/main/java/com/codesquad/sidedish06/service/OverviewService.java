package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.domain.dto.ResponseOverview;

import java.io.IOException;
import java.net.URISyntaxException;

public interface OverviewService {
    void save() throws IOException, URISyntaxException;

    ResponseOverview listMenu(String menu);
}
