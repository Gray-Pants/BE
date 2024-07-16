package com.poku.graypants.domain.review.application;

import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.review.persistence.Review;
import com.poku.graypants.domain.review.persistence.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewDataService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUser_UserId(userId);
    }

    public Long countByUser_UserId(Long userId) {
        return reviewRepository.countByUser_UserId(userId);
    }
}
