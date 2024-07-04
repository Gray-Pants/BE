//package com.poku.graypants.domain.product.persistence;
///**
// * @author HAYEON
// */
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import java.time.LocalDate;
//import java.util.List;
//import static jakarta.persistence.FetchType.*;
//
//@Entity
//@Table(name = "users")
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
//
//    @Column(name = "user_name", nullable = false, length = 100)
//    private String userName;
//
//    @Column(name = "password", nullable = false, length = 100)
//    private String password;
//
//    @Column(name = "email", nullable = false, length = 200)
//    private String email;
//
//    @Column(name = "social_login_type", nullable = false, length = 50)
//    private String socialLoginType;
//
//    @Enumerated(EnumType.STRING)
//    private Grade grade;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDate createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    private LocalDate updatedAt;
//
////    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL, fetch = LAZY)
////    @JoinColumn(name = "order_id")
////    private List<Order> orders;
//
//    @Builder
//    public User(String userName, String email, String password, String grade) {
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//        this.grade = Grade.valueOf(grade);
//    }
//}
