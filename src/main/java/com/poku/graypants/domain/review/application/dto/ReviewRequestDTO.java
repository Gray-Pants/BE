package com.poku.graypants.domain.review.application.dto;

import com.poku.graypants.domain.review.persistence.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequestDTO {
    private Long reviewId;
    private String reviewContent;
    private int reviewScore;
    private Long userId;
    private Long itemId;

    public Review toEntity() {
        return Review.builder()
                .reviewId(reviewId)
                .reviewContent(reviewContent)
                .reviewScore(reviewScore)
                .build();
    }

}
