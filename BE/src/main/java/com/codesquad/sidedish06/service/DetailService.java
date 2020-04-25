package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.domain.dto.ResponseDetail;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;

public interface DetailService {
    public Object save() throws URISyntaxException, JsonProcessingException;

    ResponseDetail read(String hash);
}
