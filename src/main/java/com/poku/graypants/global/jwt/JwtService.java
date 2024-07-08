package com.poku.graypants.global.jwt;

import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);
    public static final Duration ACCESS_TOKEN_DURATION = Duration.ofDays(1);

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    /**
     * 생성된 리프레시 토큰을 쿠키에 저장하는 메소드
     *
     * @param user       Long
     * @param newRefreshToken String
     */
    public void saveRefreshToken(User user, final String newRefreshToken) {
        user.updateRefreshToken(newRefreshToken);
        userRepository.save(user);
    }

    public void addRefreshTokenToCookie(final HttpServletRequest request, final HttpServletResponse response,
                                         final String refreshToken) {
        int cookieMaxAge = (int) REFRESH_TOKEN_DURATION.toSeconds();
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    public String generateToken(User user, Duration duration) {
        return jwtProvider.generateToken(user, duration);
    }
}
