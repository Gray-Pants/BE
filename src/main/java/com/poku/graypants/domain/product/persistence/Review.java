//package com.poku.graypants.domain.product.persistence;
//
//import jakarta.persistence.*;
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long review;
//
//    @Column(name = "review_content", length = 500)
//    private String reviewContent;
//
//    @Min(1)
//    @Max(10)
//    @Column(name = "review_score")
//    private int reviewScore;
//
//    @Builder
//    public Review(String reviewContent, int reviewScore) {
//        this.reviewContent = reviewContent;
//        this.reviewScore = reviewScore;
//    }
//}