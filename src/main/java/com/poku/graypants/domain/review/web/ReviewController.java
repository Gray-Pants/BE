package com.poku.graypants.domain.review.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.review.application.ReviewService;
import com.poku.graypants.domain.review.application.dto.ReviewRequestDTO;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<ApiResult<ReviewResponseDTO>> createReview(@RequestBody ReviewRequestDTO request,
                                                          @AuthenticationPrincipal Long userId) {
        ReviewResponseDTO response = reviewService.createReview(request, userId);
        return ResponseEntity.ok(success(response));
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