package com.codesquad.sidedish06.utils;

import java.util.ArrayList;
import java.util.List;

import static com.codesquad.sidedish06.utils.JsonUtils.BASE_URL;

public class UrlUtils {

    public static final String MAIN_URL = "http://52.79.117.147";
    public static final String GITHUB_LOGIN_URL = "https://github.com/login/oauth/authorize/?client_id=4946b46078dcaa5adfa6&scope=user%20public_repo";

    public static List<String> urls() {
        List<String> menuUrls = new ArrayList<>();

        menuUrls.add(BASE_URL + "/main");
        menuUrls.add(BASE_URL + "/soup");
        menuUrls.add(BASE_URL + "/side");

        return menuUrls;
    }
}
