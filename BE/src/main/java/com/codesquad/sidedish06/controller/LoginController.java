package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.service.LoginService;
import com.codesquad.sidedish06.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@Log4j2
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/githublogin")
    public RedirectView githubLogin(@RequestParam("code") String code,
                                    HttpServletResponse response) {
        loginService.requestAccessToken(code);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(UrlUtils.MAIN_URL);
        try {
            response.setHeader("login-check", "true");
            return redirectView;
        } catch (RuntimeException e) {
            response.setHeader("login-check", "false");
            redirectView.setUrl(UrlUtils.GITHUB_LOGIN_URL);
            return redirectView;
        }
    }
}
