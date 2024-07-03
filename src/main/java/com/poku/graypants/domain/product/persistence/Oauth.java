package com.poku.graypants.domain.product.persistence;
/**
/**
 * @author HAYEON
 */
import com.poku.graypants.domain.product.persistence.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oauthId;

    @Column(name = "oauth_provider", nullable = false, length = 100)
    private String ouathProvider;

    @Column(name = "outh_provider_id", nullable = false, length = 100)
    private String outhProviderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
