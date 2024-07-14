package com.poku.graypants.domain.payments.web;


import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.payments.application.KakaoPayService;
import com.poku.graypants.domain.payments.persistence.*;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

@RestController
@RequestMapping("api/payments/kakaoPay")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/ready")
    public ResponseEntity<ApiResult<KakaoPayReadyResponseDto>> kakaoPayReady(@RequestBody KakaoPayClientReadyRequestDto kakaoPayClientReadyRequestDto, @AuthenticationPrincipal Long userId) {
        return new ResponseEntity<>(success(kakaoPayService.kakaoPayReady(kakaoPayClientReadyRequestDto, userId)), new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/approve")
    public ResponseEntity<Void> kakaoPayApprove(@RequestBody KakaoPayClientApproveRequestDto kakaoPayClientApproveRequestDto, @AuthenticationPrincipal Long userId) {
        KakaoPayApproveResponseDto kakaoPayApproveResponseDto = kakaoPayService.kakaoPayApprove(kakaoPayClientApproveRequestDto, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/cancel")
    public KakaoPayCancelResponseDto kakaoPayCancel(KakaoPayClientCancelRequestDto kakaoPayClientCancelRequestDto) {
        return kakaoPayService.kakaoPayCancel(kakaoPayClientCancelRequestDto);
    }
}
