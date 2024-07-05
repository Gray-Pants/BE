package com.poku.graypants.domain.auth.application;

import static com.poku.graypants.global.config.oauth.OAuth2SuccessHandler.REDIRECT_PATH;
import static com.poku.graypants.global.jwt.JwtService.ACCESS_TOKEN_DURATION;
import static com.poku.graypants.global.jwt.JwtService.REFRESH_TOKEN_DURATION;

import com.poku.graypants.domain.auth.application.dto.EmailLoginRequestDto;
import com.poku.graypants.domain.auth.application.dto.EmailSignupRequestDto;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import com.poku.graypants.global.jwt.JwtService;
import com.poku.graypants.global.util.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class EmailAuthService {

    private final RedisUtil redisUtil;
    private final UserService userService;
    private final JwtService jwtService;
    public String signup(HttpServletRequest httpRequest, HttpServletResponse httpResponse, EmailSignupRequestDto request) {
        //메일인증확인 번호 확인
        String verificationCode = (String)redisUtil.getData("VerificationCode " + request.getEmail());
        if(!request.getVerificationCode().equals(verificationCode)) {
            throw new GrayPantsException(ExceptionStatus.INVALID_VERIFICATION_CODE);
        }
        //메일인증확인 번호 삭제
        redisUtil.deleteData("VerificationCode " + request.getEmail());

        //유저생성
        User user = userService.saveEmailUser(request.getEmail(), request.getName(), request.getPassword());

        return saveTokenAndGetTargetUri(httpRequest, httpResponse, user);
    }

    public String login(HttpServletRequest httpRequest, HttpServletResponse httpResponse, EmailLoginRequestDto request) {
        User user = userService.getUserByEmail(request.getEmail());

        if(!userService.matchPassword(request.getPassword(), user.getPassword())) {
            throw new GrayPantsException(ExceptionStatus.INVALID_PASSWORD);
        }

        return saveTokenAndGetTargetUri(httpRequest, httpResponse, user);
    }

    private String saveTokenAndGetTargetUri(HttpServletRequest httpRequest, HttpServletResponse httpResponse, User user) {

        String accessToken = jwtService.generateToken(user, ACCESS_TOKEN_DURATION);
        String refreshToken = jwtService.generateToken(user, REFRESH_TOKEN_DURATION);

        //리프레시 토큰 저장
        jwtService.saveRefreshToken(user, refreshToken);

        //리프레시 토큰 쿠키에 저장
        jwtService.addRefreshTokenToCookie(httpRequest, httpResponse, refreshToken);

        return UriComponentsBuilder.fromUriString(REDIRECT_PATH).queryParam("token", accessToken).build().toUriString();
    }
}
