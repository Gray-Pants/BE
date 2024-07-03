package com.poku.graypants.domain.auth.persistence;

import com.poku.graypants.domain.user.persistence.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OAUTH")
@NoArgsConstructor
@Getter
public class OAuth {
    @Id
    @Column(name = "oauth_id")
    private String oAuthId;

    @Column(name = "oauth_provider", length = 100)
    private String provider;

    @Column(name = "oauth_provider_id", length = 100)
    private String providerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
    @Builder
    public OAuth(String oAuthId, String provider, String providerId, User user) {
        this.oAuthId = oAuthId;
        this.provider = provider;
        this.providerId = providerId;
        this.user = user;
    }
}
