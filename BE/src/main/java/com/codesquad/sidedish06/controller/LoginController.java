package com.codesquad.sidedish06.controller;

import com.codesquad.sidedish06.service.LoginService;
import com.codesquad.sidedish06.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/githublogin")
    public ResponseEntity<String> githubLogin(@RequestParam("code") String code,
                                              HttpServletResponse response) {
        loginService.requestAccessToken(code);
        try {
            Cookie cookie = new Cookie("login-check", "true");
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            response.sendRedirect(UrlUtils.MAIN_URL);
            return new ResponseEntity<>(HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
