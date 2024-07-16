package com.poku.graypants.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Base64;
import org.springframework.util.SerializationUtils;

/**
 * CookieUtil 쿠키를 추가하거나 삭제하는 유틸리티 클래스
 */
public class CookieUtil {
    /**
     * 쿠키를 추가하는 메소드
     *
     * @param response HttpServletResponse 객체
     * @param name     쿠키 이름
     * @param value    쿠키 값
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    /**
     * 쿠키를 삭제하는 메소드
     *
     * @param request  HttpServletRequest 객체
     * @param response HttpServletResponse 객체
     * @param name     삭제할 쿠키 이름
     */
    public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response,
                                    final String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return;
            }
        }
    }

    /**
     * 객체를 직렬화하여 문자열로 변환하는 메소드
     *
     * @param object 직렬화할 객체
     * @return 직렬화된 객체를 Base64로 인코딩한 문자열
     */
    public static String serialize(final Object object) {
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    /**
     * 쿠키에서 객체를 역직렬화하는 메소드
     *
     * @param cookie 역직렬화할 쿠키
     * @param cls    역직렬화할 클래스
     * @return 역직렬화된 객체
     */
    public static <T> T deserialize(final Cookie cookie, final Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(
                        Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}
