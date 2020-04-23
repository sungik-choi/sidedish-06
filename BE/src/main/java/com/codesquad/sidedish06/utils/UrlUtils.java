package com.codesquad.sidedish06.utils;

import java.util.ArrayList;
import java.util.List;

import static com.codesquad.sidedish06.utils.JsonUtils.BASE_URL;

public class UrlUtils {

    public static List<String> urls() {
        List<String> menuUrls = new ArrayList<>();

        menuUrls.add(BASE_URL + "/main");
        menuUrls.add(BASE_URL + "/soup");
        menuUrls.add(BASE_URL + "/side");

        return menuUrls;
    }
}
