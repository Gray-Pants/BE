package com.poku.graypants.domain.auth.web;

import com.poku.graypants.domain.auth.application.EmailAuthService;
import com.poku.graypants.domain.auth.application.dto.EmailLoginRequestDto;
import com.poku.graypants.domain.auth.application.dto.EmailSignupRequestDto;
import com.poku.graypants.global.jwt.JwtService;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final EmailAuthService emailAuthService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<ApiResult<?>> emailLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestBody  EmailLoginRequestDto request) {

        String accessToken = emailAuthService.userEmailLogin(httpRequest, httpResponse, request);
        httpResponse.setHeader("access-token", accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResult<?>> emailSignup(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestBody EmailSignupRequestDto request) {
        String accessToken = emailAuthService.signup(httpRequest, httpResponse, request);
        httpResponse.setHeader("access-token", accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/store-login")
    public ResponseEntity<ApiResult<?>> storeLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                                   @RequestBody EmailLoginRequestDto request) {

        log.info("store login request: {}", request);
        String accessToken = emailAuthService.storeEmailLogin(httpRequest, httpResponse, request);

        httpResponse.setHeader("access-token", accessToken);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResult<?>> refresh(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        jwtService.refresh(httpRequest, httpResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
