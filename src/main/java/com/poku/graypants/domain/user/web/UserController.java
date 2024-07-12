package com.poku.graypants.domain.user.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.review.application.dto.ReviewResponseDTO;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.application.dto.MyProfileResponseDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/me")
    public ResponseEntity<ApiResult<MyProfileResponseDto>> me(Authentication authentication) {
        MyProfileResponseDto response = userService.getMyProfile((Long)authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/reviews")
    public ResponseEntity<ApiResult<List<ReviewResponseDTO>>> reviews(Authentication authentication) {
        List<ReviewResponseDTO> response = userService.getMyReviews((Long)authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

}
