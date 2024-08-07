package com.poku.graypants.global.config.oauth.info;

import java.util.Map;

/**
 * OAuth2 OAuth2MemberInfo 팩토리
 */
public class OAuth2UserInfoFactory {

    /**
     * OAuth2 공급자와 속성을 받아 OAuth2MemberInfo를 생성한다.
     *
     * @param provider   OAuth2 공급자
     * @param attributes OAuth2 속성
     * @return OAuth2MemberInfo
     */
    public static OAuth2UserInfo createOAuth2MemberInfo(String oAuthType, Map<String, Object> attributes) {
        return switch (oAuthType) {
            case "kakao" -> new KakaoUserInfo(attributes);
            case "google" -> new GoogleUserInfo(attributes);
            case "naver" -> new NaverUserInfo((Map<String, Object>) attributes.get("response"));
            default -> throw new IllegalArgumentException("지원하지 않는 OAuth2 공급자입니다.");
        };
    }
}