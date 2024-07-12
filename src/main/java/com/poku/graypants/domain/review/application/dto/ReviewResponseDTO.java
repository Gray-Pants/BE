package com.poku.graypants.domain.review.application.dto;

import com.poku.graypants.domain.review.persistence.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long reviewId;
    private String reviewContent;
    private int reviewScore;
    private Long userId;
    private Long itemId;

    public static ReviewResponseDTO fromEntity(Review review) {
        return new ReviewResponseDTO(
                review.getReviewId(),
                review.getReviewContent(),
                review.getReviewScore(),
                review.getUser().getId(),
                review.getItem().getItemId()
        );
    }
}
