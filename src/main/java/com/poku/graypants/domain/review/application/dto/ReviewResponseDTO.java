package com.poku.graypants.domain.review.application.dto;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.review.persistence.Review;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long reviewId;
    private String reviewContent;
    private int reviewScore;
    private Long userId;
    private String createdAt;
    private ItemResponseDto item;
    private String username;

    public static ReviewResponseDTO fromEntity(Review review) {
        return new ReviewResponseDTO(
                review.getReviewId(),
                review.getReviewContent(),
                review.getReviewScore(),
                review.getUser().getId(),
                review.getCreatedAt().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd"))),
                new ItemResponseDto(review.getItem()),
                review.getUser().getUsername()
        );
    }
}
