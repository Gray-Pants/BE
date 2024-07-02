package com.poku.graypants.global.config.oauth.info;

public interface OAuth2UserInfo {
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
