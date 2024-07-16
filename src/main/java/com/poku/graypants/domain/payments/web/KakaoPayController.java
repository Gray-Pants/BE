package com.poku.graypants.domain.payments.web;


import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.payments.application.KakaoPayService;
import com.poku.graypants.domain.payments.persistence.dto.*;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

@Slf4j
@RestController
@RequestMapping("api/payments/kakaoPay")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/ready")
    public ResponseEntity<ApiResult<KakaoPayReadyResponseDto>> kakaoPayReady(@RequestBody KakaoPayClientReadyRequestDto kakaoPayClientReadyRequestDto, @AuthenticationPrincipal Long userId) {
        return new ResponseEntity<>(success(kakaoPayService.kakaoPayReady(kakaoPayClientReadyRequestDto, userId)), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity<Void> kakaoPayApprove(@RequestBody KakaoPayClientApproveRequestDto kakaoPayClientApproveRequestDto) {
        KakaoPayApproveResponseDto kakaoPayApproveResponseDto = kakaoPayService.kakaoPayApprove(kakaoPayClientApproveRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/cancel")
    public ResponseEntity<ApiResult<KakaoPayCancelResponseDto>> kakaoPayCancel(KakaoPayClientCancelRequestDto kakaoPayClientCancelRequestDto) {
        return new ResponseEntity<>(success(kakaoPayService.kakaoPayCancel(kakaoPayClientCancelRequestDto)), HttpStatus.OK);
    }
}
