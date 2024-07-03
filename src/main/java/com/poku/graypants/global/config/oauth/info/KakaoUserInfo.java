package com.poku.graypants.global.config.oauth.info;

import java.util.Map;

/**
 * 카카오 OAuth2 회원 정보
 */
public class KakaoUserInfo implements OAuth2UserInfo {
    private final Map<String, Object> attributes;
    private final Map<String, Object> kakaoAccountAttributes;
    private final Map<String, Object> profileAttributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.kakaoAccountAttributes = (Map<String, Object>) attributes.get("kakao_account");
        this.profileAttributes = (Map<String, Object>) kakaoAccountAttributes.get("profile");

    }


    /**
     * 카카오 회원 정보에서 공급자 아이디를 반환한다.
     *
     * @return 카카오 회원 정보에서 공급자 아이디
     */
    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    /**
     * 카카오 회원 정보에서 공급자를 반환한다.
     *
     * @return "kakao"
     */
    @Override
    public String getProvider() {
        return "kakao";
    }

    /**
     * 카카오 회원 정보에서 이름을 반환한다.
     *
     * @return 카카오 회원 정보에서 이름
     */
    @Override
    public String getName() {
        return profileAttributes.get("nickname").toString();
    }

    /**
     * 카카오 회원 정보에서 이메일을 반환한다.
     *
     * @return 카카오 회원 정보에서 이메일
     */
    @Override
    public String getEmail() {
        return kakaoAccountAttributes.get("email").toString();
    }
}