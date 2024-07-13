package com.poku.graypants.domain.review.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.OrderItemService;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.review.application.dto.ReviewRequestDTO;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.review.persistence.Review;
import com.poku.graypants.domain.review.persistence.ReviewRepository;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final OrderItemService orderItemService;

    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO, Long userId) {
        User user = userService.getVerifyUserByUserId(userId);
        OrderItem orderItem = orderItemService.getVerifyOrderItemByOrderItemId(reviewRequestDTO.getOrderItemId());

        Review review = Review.builder()
                .reviewContent(reviewRequestDTO.getReviewContent())
                .reviewScore(reviewRequestDTO.getReviewScore())
                .user(user)
                .item(orderItem.getItem())
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewResponseDTO.fromEntity(savedReview);
    }

    public ReviewResponseDTO getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.REVIEW_NOT_FOUND));
        return ReviewResponseDTO.fromEntity(review);
    }

    public List<ReviewResponseDTO> getReviewsByItemId(Long itemId) {
        return reviewRepository.findByItem_ItemId(itemId).stream()
                .map(ReviewResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ReviewResponseDTO updateReview(Long reviewId, ReviewRequestDTO reviewRequestDTO) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.REVIEW_NOT_FOUND));

        Review updatedReview = Review.builder()
                .reviewId(existingReview.getReviewId())
                .reviewContent(reviewRequestDTO.getReviewContent())
                .reviewScore(reviewRequestDTO.getReviewScore())
                .user(existingReview.getUser())
                .item(existingReview.getItem())
                .build();

        Review savedReview = reviewRepository.save(updatedReview);
        return ReviewResponseDTO.fromEntity(savedReview);
    }

    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new GrayPantsException(ExceptionStatus.REVIEW_NOT_FOUND);
        }
        reviewRepository.deleteById(reviewId);
    }
}