package com.poku.graypants.domain.review.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "REVIEWS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "review_content", length = 1000)
    private String reviewContent;

    @Column(name = "review_score")
    private int reviewScore;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Review(Long reviewId, String reviewContent, int reviewScore, User user, Item item) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewScore = reviewScore;
        this.user = user;
        this.item = item;
    }

    public Review updateContent(String reviewContent) {
        return Review.builder()
                .reviewContent(reviewContent)
                .reviewScore(this.reviewScore)
                .user(this.user)
                .item(this.item)
                .build();
    }

    public Review updateScore(int reviewScore) {
        return Review.builder()
                .reviewContent(this.reviewContent)
                .reviewScore(reviewScore)
                .user(this.user)
                .item(this.item)
                .build();
    }
}
