package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.order.application.OrderDataService;
import com.poku.graypants.domain.review.application.ReviewDataService;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.user.application.dto.MyProfileResponseDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReviewDataService reviewDataService;
    private final OrderDataService orderDataService;

    private final String DEFAULT_ROLE = "ROLE_DEFAULT";

    public User saveOrGetUserByOAuth2Info(OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElseGet(() -> saveUser(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName()));
    }

    public User saveUser(String email, String username) {
        return userRepository.save(User.builder()
                .grade(DEFAULT_ROLE)
                .email(email)
                .username(username)
                .build());
    }

    public User getUser(Long userId) {
        return findVerifyUserById(userId);
    }

    private User findVerifyUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new GrayPantsException(ExceptionStatus.USER_NOT_FOUND));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new GrayPantsException(ExceptionStatus.USER_NOT_FOUND));
    }

    public EmailAuthenticateAble saveEmailUser(String email, String name, String password) {
        return userRepository.save(User.builder()
                .grade(DEFAULT_ROLE)
                .email(email)
                .username(name)
                .password(password)
                .build());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    public MyProfileResponseDto getMyProfile(Long user_id) {
        User user = getUser(user_id);
        Long reviewCount = 0L; // TODO: reviewCount
        Long orderCount = orderDataService.countByUser_UserId(user_id);
        return MyProfileResponseDto.builder()
                .username(user.getUsername())
                .reviewCount(reviewCount)
                .orderCount(orderCount)
                .build();
    }

    public List<ReviewResponseDTO> getMyReviews(Long userId) {
        return reviewDataService.getReviewsByUserId(userId).stream().map(ReviewResponseDTO::fromEntity).toList();
    }
}
