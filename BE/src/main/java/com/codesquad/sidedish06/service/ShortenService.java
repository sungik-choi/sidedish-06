package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.domain.dto.Shorten;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.codesquad.sidedish06.utils.JsonUtils.BASE_URL;
import static com.codesquad.sidedish06.utils.JsonUtils.data;

@Service
public class ShortenService {

    public Shorten[] saveShortenInformation(String menu) throws URISyntaxException, IOException {
        String url = BASE_URL + menu;
        String data = data(url);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonNode = objectMapper.readValue(data, JsonNode.class).get("body");
        Shorten[] shortens = objectMapper.convertValue(jsonNode, Shorten[].class);
        return shortens;
    }
}
