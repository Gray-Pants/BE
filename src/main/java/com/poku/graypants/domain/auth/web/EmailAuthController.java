package com.poku.graypants.domain.auth.web;

import com.poku.graypants.domain.auth.application.EmailAuthService;
import com.poku.graypants.domain.auth.application.dto.EmailLoginRequestDto;
import com.poku.graypants.domain.auth.application.dto.EmailSignupRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/email-auth")
@RequiredArgsConstructor
public class EmailAuthController {

    private final EmailAuthService emailAuthService;
    @PostMapping("/login")
    public ResponseEntity<ApiResult<?>> emailLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestBody  EmailLoginRequestDto request) {

        String targetUri = emailAuthService.login(httpRequest, httpResponse, request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("uri", targetUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResult<?>> emailSignup(HttpServletRequest httpRequest, HttpServletResponse httpResponse, @RequestBody EmailSignupRequestDto request) {
        String targetUri = emailAuthService.signup(httpRequest, httpResponse, request);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("uri", targetUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
