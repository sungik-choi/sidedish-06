package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.domain.dto.ResponseDetail;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface DetailService {
    public void save() throws URISyntaxException, JsonProcessingException;

    public ResponseDetail read(String hash);
}
