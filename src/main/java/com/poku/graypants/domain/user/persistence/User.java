package com.poku.graypants.domain.user.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", length = 100)
    private String username;

    @Column(name = "email", length = 200, unique = true)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name="grade", length=50)
    private String grade;

    @Column(name="refresh_token")
    private String refreshToken;

    //분리 필요
    @Column(name = "oauth2_id", length = 100, unique = true)
    private String oauth2Id;

    @Column(name = "provider", length = 100)
    private String provider;

    @Column(name = "provider_id", length = 100, unique = true)
    private String providerId;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    @Builder
    public User(String username, String email, String password, String grade, String oauth2Id, String provider, String providerId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.grade = grade;
        this.oauth2Id = oauth2Id;
        this.provider = provider;
        this.providerId = providerId;
    }
}
