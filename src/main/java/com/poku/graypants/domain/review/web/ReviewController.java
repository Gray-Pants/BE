package com.poku.graypants.domain.review.web;

import com.poku.graypants.domain.review.application.ReviewService;
import com.poku.graypants.domain.review.application.dto.ReviewRequestDTO;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ApiResult<ReviewResponseDTO>> createReview(@RequestBody ApiResult<ReviewRequestDTO> request) {
        ReviewRequestDTO reviewRequestDTO = request.getResponse();
        ReviewResponseDTO createdReview = reviewService.createReview(reviewRequestDTO);
        return ResponseEntity.ok(success(createdReview));
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ApiResult<ReviewResponseDTO>> getReview(@PathVariable Long reviewId) {
        ReviewResponseDTO review = reviewService.getReviewById(reviewId);
        return ResponseEntity.ok(success(review));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ApiResult<List<ReviewResponseDTO>>> getReviewsByItemId(@PathVariable Long itemId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByItemId(itemId);
        return ResponseEntity.ok(success(reviews));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResult<ReviewResponseDTO>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ApiResult<ReviewRequestDTO> request) {
        ReviewRequestDTO reviewRequestDTO = request.getResponse();
        ReviewResponseDTO updatedReview = reviewService.updateReview(reviewId, reviewRequestDTO);
        return ResponseEntity.ok(success(updatedReview));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}