package com.poku.graypants.domain.user.persistence;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.order.persistence.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@NoArgsConstructor
public class User implements EmailAuthenticateAble {

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

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

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public Long getId() {
        return userId;
    }
}
