package com.poku.graypants.global.config.oauth.info;

import java.util.Map;

/**
 * 구글 OAuth2 회원 정보
 */
public class GoogleUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    /**
     * 구글 회원 정보 생성자
     *
     * @param attributes 구글 회원 정보
     */
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * 구글 회원 정보에서 공급자 아이디를 반환한다.
     *
     * @return 구글 회원 정보에서 공급자 아이디
     */
    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    /**
     * 구글 회원 정보에서 공급자를 반환한다.
     *
     * @return "google"
     */
    @Override
    public String getProvider() {
        return "google";
    }

    /**
     * 구글 회원 정보에서 이름을 반환한다.
     *
     * @return 구글 회원 정보에서 이름
     */
    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    /**
     * 구글 회원 정보에서 이메일을 반환한다.
     *
     * @return 구글 회원 정보에서 이메일
     */
    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }
}