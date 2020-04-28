package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.config.GithubPropertyConfig;
import com.codesquad.sidedish06.domain.dto.GithubTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final GithubPropertyConfig githubPropertyConfig;

    public GithubTokenDto requestAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json");
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", githubPropertyConfig.getClientId());
        requestPayload.put("client_secret", githubPropertyConfig.getClientSecret());
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(githubPropertyConfig.getUrl(), request, GithubTokenDto.class);
        return (GithubTokenDto) response.getBody();
    }

    public String getAuthorizationValue(GithubTokenDto token) {
        StringBuilder sb = new StringBuilder();
        sb.append(token.getTokenType());
        sb.append(" ");
        sb.append(token.getAccessToken());
        return  sb.toString();
    }
}
