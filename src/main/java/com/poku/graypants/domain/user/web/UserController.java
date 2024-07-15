package com.poku.graypants.domain.user.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.amazonaws.Response;
import com.poku.graypants.domain.addr.application.Dto.AddrRequestDto;
import com.poku.graypants.domain.addr.application.Dto.AddrResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.application.dto.ChangeUserNameResponseDto;
import com.poku.graypants.domain.user.application.dto.MemberInformationResponseDto;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/reviews/requests")
    public ResponseEntity<ApiResult<List<OrderItemResponseDto>>> myReviewRequests(@AuthenticationPrincipal Long userId) {
        List<OrderItemResponseDto> response = userService.getReviewRequestsByUserId(userId);
        return new ResponseEntity<>(success(response), new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/info")
    public ResponseEntity<ApiResult<MemberInformationResponseDto>> getMyInfo(@AuthenticationPrincipal Long userId) {
        MemberInformationResponseDto response = userService.getMyInfo(userId);
        return new ResponseEntity<>(success(response), new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/username")
    public ResponseEntity<?> changeUserName(@RequestBody ChangeUserNameResponseDto request,
                                           @AuthenticationPrincipal Long userId) {
        userService.changeUserName(request, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/newaddr")
    public ResponseEntity<ApiResult<AddrResponseDto>> addNewAddr(@RequestBody AddrRequestDto request,
                                                                 @AuthenticationPrincipal Long userId) {
        AddrResponseDto response = userService.addNewAddr(request, userId);
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/mylikes/{itemId}")
    public ResponseEntity<ApiResult<Boolean>> isLikedItem(@AuthenticationPrincipal Long userId,@PathVariable Long itemId) {
        Boolean response = userService.isLikedItem(userId, itemId);
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }
}
