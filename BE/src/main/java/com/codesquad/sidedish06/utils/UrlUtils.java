package com.codesquad.sidedish06.utils;

import java.util.Arrays;
import java.util.List;

public class UrlUtils {

    public static final String BASE_URL = "https://h3rb9c0ugl.execute-api.ap-northeast-2.amazonaws.com/develop/baminchan";

    private UrlUtils() {}

    public static List<String> urls() {
        return Arrays.asList(
                BASE_URL + "/main",
                BASE_URL + "/soup",
                BASE_URL + "/side"
        );
    }
}
