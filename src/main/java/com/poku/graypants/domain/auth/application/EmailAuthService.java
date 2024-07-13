package com.poku.graypants.domain.auth.application;

import static com.poku.graypants.global.jwt.JwtService.ACCESS_TOKEN_DURATION;
import static com.poku.graypants.global.jwt.JwtService.REFRESH_TOKEN_DURATION;

import com.poku.graypants.domain.auth.application.dto.EmailLoginRequestDto;
import com.poku.graypants.domain.auth.application.dto.EmailSignupRequestDto;
import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import com.poku.graypants.global.jwt.JwtService;
import com.poku.graypants.global.util.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailAuthService {

    private final RedisUtil redisUtil;
    private final UserService userService;
    private final JwtService jwtService;
    private final StoreService storeService;
    private final PasswordEncoder passwordEncoder;

    public String signup(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                         EmailSignupRequestDto request) {
        //메일인증확인 코드 확인
        String verificationCode = (String) redisUtil.getData(
                "VerificationCode " + request.getRole() + request.getEmail());
        if (!request.getVerificationCode().equals(verificationCode)) {
            throw new GrayPantsException(ExceptionStatus.INVALID_VERIFICATION_CODE);
        }
        //메일인증확인 코드 삭제
        redisUtil.deleteData("VerificationCode " + request.getRole() + request.getEmail());

        //유저생성
        EmailAuthenticateAble entity = null;
        String password = passwordEncoder.encode(request.getPassword());
        if (request.getRole().equals("user")) {
            entity = userService.saveEmailUser(request.getEmail(), request.getName(), password);
        } else if (request.getRole().equals("store")) {
            entity = storeService.saveStore(request.getEmail(), request.getName(), password);
        } else {
            throw new GrayPantsException(ExceptionStatus.INVALID_ROLE);
        }

        return saveTokenAndGetAccessToken(httpRequest, httpResponse, entity);
    }

    public String userEmailLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                 EmailLoginRequestDto request) {
        User user = userService.getUserByEmail(request.getEmail());
        if (user == null) {
            throw new GrayPantsException(ExceptionStatus.INVALID_PASSWORD);
        }
        return login(httpRequest, httpResponse, user, request.getPassword());
    }

    public String storeEmailLogin(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                  EmailLoginRequestDto request) {
        Store store = storeService.getStoreByEmail(request.getEmail());

        if (store == null) {
            throw new GrayPantsException(ExceptionStatus.INVALID_PASSWORD);
        }
        return login(httpRequest, httpResponse, store, request.getPassword());
    }

    private String login(HttpServletRequest httpRequest, HttpServletResponse httpResponse, EmailAuthenticateAble entity,
                         String password) {

        if (!validationPasswordByEmail(entity, password)) {
            throw new GrayPantsException(ExceptionStatus.INVALID_PASSWORD);
        }

        return saveTokenAndGetAccessToken(httpRequest, httpResponse, entity);
    }

    private String saveTokenAndGetAccessToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                              EmailAuthenticateAble entity) {

        String accessToken = jwtService.generateToken(entity, ACCESS_TOKEN_DURATION);
        String refreshToken = jwtService.generateToken(entity, REFRESH_TOKEN_DURATION);

        //리프레시 토큰 저장
        jwtService.saveRefreshToken(entity, refreshToken);

        //리프레시 토큰 쿠키에 저장
        jwtService.addRefreshTokenToCookie(httpRequest, httpResponse, refreshToken);
        return accessToken;
    }


    public boolean validationPasswordByEmail(EmailAuthenticateAble entity, String password) {
        //인코딩 로직 필요
        return passwordEncoder.matches(password, entity.getPassword());
    }
}
