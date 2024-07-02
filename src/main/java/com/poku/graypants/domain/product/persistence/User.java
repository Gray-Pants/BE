package com.poku.graypants.domain.product.persistence;
/**
 * @author HAYEON
 */

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "social_login_type", nullable = false, length = 50)
    private String socialLoginType;

    //Enum
    @Column(name = "grade", nullable = false, length = 50)
    private String grade;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
}
