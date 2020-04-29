package com.codesquad.sidedish06.utils;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class JsonUtils {

    private static final RestTemplate restTemplate = new RestTemplate();

    private JsonUtils() {}

    public static String data(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return restTemplate.getForObject(uri, String.class);
    }
}
