package com.codesquad.sidedish06.utils;

import com.codesquad.sidedish06.domain.entity.Overview;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JsonUtils {
    public static final String BASE_URL = "https://h3rb9c0ugl.execute-api.ap-northeast-2.amazonaws.com/develop/baminchan";

    public static final RestTemplate restTemplate = new RestTemplate();

    public static String data(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return restTemplate.getForObject(uri, String.class);
    }

    public static Overview[] listOverview(String menu) throws URISyntaxException, IOException {
        String url = BASE_URL + menu;
        String data = data(url);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode jsonNode = objectMapper.readValue(data, JsonNode.class).get("body");
        Overview[] overviews = objectMapper.convertValue(jsonNode, Overview[].class);
        return overviews;
    }
}
