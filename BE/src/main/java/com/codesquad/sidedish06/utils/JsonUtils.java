package com.codesquad.sidedish06.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class JsonUtils {

    public static final String BASE_URL = "https://h3rb9c0ugl.execute-api.ap-northeast-2.amazonaws.com/develop/baminchan";

    public static final String[] HASHES = {
            "H9881", "HDF4C", "H7F20", "HA567", "H206E",
            "H75A2", "H4AAA", "H206E", "HA8B9", "E055F",
            "FDAEB", "H762E", "H05FB", "H0699", "HBDEF",
            "H82A2", "H213E", "H7A16", "HBDEF", "HDF73",
            "HF778", "HFB53", "H077F", "H4665", "H1AA9",
            "HEDFB", "H4C5E", "H8676", "HAE92", "HAA29",
            "H296C", "H5152", "HAC68", "HBDEF", "H72C3",
            "HA6EE", "H8CD0", "HE2E9", "HAA47", "H3254",
            "H26C7", "HFFF9", "HBBCC", "H1939", "H8EA5",
            "H602F", "H9F0B", "H0FC6", "HCCFE", "HB9C1"
    };

    private static final RestTemplate restTemplate = new RestTemplate();

    private JsonUtils() {
    }

    public static String data(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return restTemplate.getForObject(uri, String.class);
    }
}
