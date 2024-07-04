//package com.poku.graypants.domain.product.persistence;
///**
// * @author HAYEON
// */
//import com.poku.graypants.domain.product.persistence.User;
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Like {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long likeId;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDate createdAt;
//
//    @Column(name =  "updated_at", nullable = false)
//    private LocalDate updatedAt;
//
//    @ManyToOne(fetch = FetchType.LAZY) //관계가 실제로 사용될 때까지 DB로부터 관련 엔티티를 로드하지 않는다는 것을 의미한다. 즉 customer 엔티티에 접근하기 전까지는 관련 데이터가 로드되지 않는다.(성능 최적화를 위해 사용)
//    @JoinColumn(name = "user_id")
//    private User user;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name="item_id")
////    private Item item;
//
//
//}
