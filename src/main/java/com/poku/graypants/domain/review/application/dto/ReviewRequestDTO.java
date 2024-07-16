package com.poku.graypants.domain.review.application.dto;

import com.poku.graypants.domain.review.persistence.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequestDTO {
    private String reviewContent;
    private int reviewScore;
    private Long orderItemId;
}
