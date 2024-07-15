package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.addr.application.AddrService;
import com.poku.graypants.domain.addr.application.Dto.AddrRequestDto;
import com.poku.graypants.domain.addr.application.Dto.AddrResponseDto;
import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.like.application.LikeDataService;
import com.poku.graypants.domain.order.application.OrderDataService;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.orderItem.application.OrderItemDataService;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.review.application.ReviewDataService;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.user.application.dto.ChangeUserNameResponseDto;
import com.poku.graypants.domain.user.application.dto.MemberInformationResponseDto;
import com.poku.graypants.domain.user.application.dto.MyProfileResponseDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ReviewDataService reviewDataService;

    private final OrderDataService orderDataService;

    private final OrderItemDataService orderItemDataService;

    private final LikeDataService likeDataService;

    private final AddrService addrService;


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
        return getVerifyUserByUserId(userId);
    }

    public User getVerifyUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.USER_NOT_FOUND));
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
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
        Long reviewCount = reviewDataService.countByUser_UserId(user_id); // TODO: reviewCount
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

    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        return orderDataService.getOrdersByUserId(userId).stream().map(OrderResponseDto::new).toList();
    }

    public List<OrderItemResponseDto> getReviewRequestsByUserId(Long userId) {
        return orderItemDataService.getReviewRequestsByUserId(userId).stream().map(OrderItemResponseDto::new).toList();
    }

    public MemberInformationResponseDto getMyInfo(Long userId) {
        User user = getVerifyUserByUserId(userId);
        return new MemberInformationResponseDto(user);
    }

    public void changeUserName(ChangeUserNameResponseDto request, Long userId) {
        User user = getVerifyUserByUserId(userId);
        user.changeUsername(request.getChangeName());
        userRepository.save(user);
    }

    public AddrResponseDto addNewAddr(AddrRequestDto request, Long userId) {
        request.setUserId(userId);
        return addrService.createAddr(request);
    }

    public Boolean isLikedItem(Long userId, Long itemId) {
        return likeDataService.isLikedItem(userId, itemId);
    }
}
