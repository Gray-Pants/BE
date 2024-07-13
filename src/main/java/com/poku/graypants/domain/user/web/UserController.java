package com.poku.graypants.domain.user.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.application.dto.MyProfileResponseDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/me")
    public ResponseEntity<ApiResult<MyProfileResponseDto>> me(Authentication authentication) {
        MyProfileResponseDto response = userService.getMyProfile((Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/reviews")
    public ResponseEntity<ApiResult<List<ReviewResponseDTO>>> myReviews(Authentication authentication) {
        List<ReviewResponseDTO> response = userService.getMyReviews((Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders")
    public ResponseEntity<ApiResult<List<OrderResponseDto>>> myOrders(@AuthenticationPrincipal Long userId) {
        List<OrderResponseDto> orderResponseDtoList = userService.getOrdersByUserId(userId);
        return new ResponseEntity<>(success(orderResponseDtoList), new HttpHeaders(), HttpStatus.OK);
    }
}
