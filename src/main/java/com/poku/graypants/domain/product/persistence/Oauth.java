package src.main.java.com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Oauth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oauthId;

    @Column(name = "oauth_provider", nullable = false, length = 100)
    private String ouathProvider;

    @Column(name = "outh_provider_id", nullable = false, length = 100)
    private String outhProviderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
