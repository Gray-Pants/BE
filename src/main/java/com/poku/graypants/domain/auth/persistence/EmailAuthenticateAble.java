package com.poku.graypants.domain.auth.persistence;

public interface EmailAuthenticateAble {
    String getEmail();
    String getPassword();

    String getRole();

    Long getId();

    void updateRefreshToken(String refreshToken);
}
