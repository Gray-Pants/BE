package com.poku.graypants.global.jwt;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.domain.store.persistence.StoreRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import com.poku.graypants.global.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class JwtService {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final StoreRepository storeRepository;

    /**
     * 생성된 리프레시 토큰을 쿠키에 저장하는 메소드
     *
     * @param user       Long
     * @param newRefreshToken String
     */
    public void saveRefreshToken(EmailAuthenticateAble entity, final String newRefreshToken) {
        entity.updateRefreshToken(newRefreshToken);
        if(entity instanceof User)
            userRepository.save((User) entity);
        else if(entity instanceof Store)
            storeRepository.save((Store) entity);
        else
            throw new IllegalArgumentException("Invalid Role");
    }

    public void addRefreshTokenToCookie(final HttpServletRequest request, final HttpServletResponse response,
                                         final String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    public String generateToken(EmailAuthenticateAble entity, Duration duration) {
        return jwtProvider.generateToken(entity, duration);
    }

    public void refresh(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Cookie[] cookies = httpRequest.getCookies();
        if(cookies == null || cookies.length == 0)
            throw new GrayPantsException(ExceptionStatus.INVALID_REFRESH_TOKEN);
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME)) {
                String refreshToken = cookie.getValue();
                Optional<User> user = userRepository.findByRefreshToken(refreshToken);
                if(user.isPresent()) {
                    String accessToken = generateToken(user.get(), ACCESS_TOKEN_DURATION);
                    httpResponse.setHeader("access-token", accessToken);
                    return ;
                }
                break;
            }
        }
        throw new GrayPantsException(ExceptionStatus.INVALID_REFRESH_TOKEN);
    }
}
