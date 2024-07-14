package com.poku.graypants.domain.payments.web;

import com.poku.graypants.domain.order.application.OrderServiceImpl;
import com.poku.graypants.domain.payments.application.KakaoPayService;
import com.poku.graypants.domain.payments.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/payments/kakaoPay")
@RequiredArgsConstructor
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final OrderServiceImpl orderService;

    @PostMapping("/ready")
    public KakaoPayReadyResponseDto kakaoPayReady(@RequestBody KakaoPayClientReadyRequestDto kakaoPayClientReadyRequestDto) {
        return kakaoPayService.kakaoPayReady(kakaoPayClientReadyRequestDto);
    }
    @PostMapping("/approve")
    public KakaoPayApproveResponseDto kakaoPayApprove(@RequestBody KakaoPayClientApproveRequestDto kakaoPayClientApproveRequestDto) {
        KakaoPayApproveResponseDto kakaoPayApproveResponseDto = kakaoPayService.kakaoPayApprove(kakaoPayClientApproveRequestDto);
        //orderService.createOrder();
        return kakaoPayApproveResponseDto;
    }

    @PostMapping("/cancel")
    public KakaoPayCancelResponseDto kakaoPayCancel(KakaoPayClientCancelRequestDto kakaoPayClientCancelRequestDto) {
        return kakaoPayService.kakaoPayCancel(kakaoPayClientCancelRequestDto);
    }
}
