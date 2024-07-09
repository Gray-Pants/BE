package com.poku.graypants.global.config.oauth;

import static com.poku.graypants.global.jwt.JwtService.ACCESS_TOKEN_DURATION;
import static com.poku.graypants.global.jwt.JwtService.REFRESH_TOKEN_DURATION;

import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.jwt.JwtProvider;
import com.poku.graypants.global.jwt.JwtService;
import com.poku.graypants.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    public static final String REDIRECT_PATH = "/";

    private final JwtService jwtService;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

    /**
     * OAuth2 로그인 성공 시 처리하는 메소드
     *
     * @param request        HttpServletRequest 객체
     * @param response       HttpServletResponse 객체
     * @param authentication Authentication 객체
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();

        //리프레시 토큰 생성 -> 저장 -> 쿠키에 저장
        String refreshToken = jwtService.generateToken(user, REFRESH_TOKEN_DURATION);
        jwtService.saveRefreshToken(user, refreshToken);
        jwtService.addRefreshTokenToCookie(request, response, refreshToken);

        // 액세스 토큰 생성 -> 패스에 엑세스 토큰 추가
        String accessToken = jwtService.generateToken(user, ACCESS_TOKEN_DURATION);
        String targetUrl = getTargetUrl(accessToken);

        //인증 관련 설정값, 쿠키 제거
        clearAuthenticationAttributes(request, response);

        // 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }


    //인증 관련 설정값, 쿠키 제거

    /**
     * 인증 관련 설정값, 쿠키 제거하는 메소드
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     */
    private void clearAuthenticationAttributes(final HttpServletRequest request, final HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequest(request, response);
    }

    //엑세스 토큰을 패스에 추가

    /**
     * 엑세스 토큰을 패스에 추가하는 메소드
     *
     * @param token String
     * @return String
     */
    private String getTargetUrl(final String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH).queryParam("token", token).build().toUriString();
    }
}