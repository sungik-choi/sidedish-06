package com.codesquad.sidedish06.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:github.properties")
@Getter
public class GithubPropertyConfig {

    @Value("${github.accessTokenUrl}")
    private String url;

    @Value("${github.clientId}")
    private String clientId;

    @Value("${github.clientSecret}")
    private String clientSecret;
}
