package com.poku.graypants.domain.user.persistence;

import com.poku.graypants.domain.cart.persistence.Cart;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
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

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    @Builder
    public User(String username, String email, String password, String grade) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }
}
