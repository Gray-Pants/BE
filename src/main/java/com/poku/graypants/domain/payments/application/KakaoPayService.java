package com.poku.graypants.domain.payments.application;

import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.persistence.OrderStatus;
import com.poku.graypants.domain.payments.persistence.*;
import com.poku.graypants.global.util.RedisUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

        static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드

        @Value("${payments.kakaoPay.admin-key}")
        static String admin_Key;

        private final OrderService orderService;
        private final RedisUtil redisUtil;

        public KakaoPayReadyResponseDto kakaoPayReady(KakaoPayClientReadyRequestDto clientDto, Long userId) {

            redisUtil.setData(userId.toString(), clientDto, Duration.ofMinutes(10));

            KakaoPayReadyRequestDto kakaoPayReadyRequestDto = KakaoPayReadyRequestDto.builder()
                    .cid(cid)
                    .partnerOrderId("partner_order_id")
                    .partnerUserId("partner_user_id")
                    .itemName(clientDto.getItemName())
                    .quantity(clientDto.getQuantity())
                    .totalAmount(clientDto.getTotalAmount())
                    .taxFreeAmount(0)
                    .approvalUrl("http://localhost:5173/kakaoPay/approve")
                    .cancelUrl("http://localhost:8080/api/payments/kakaoPay/cancel")
                    .failUrl("http://localhost:8080/fail.html")
                    .build();

            HttpEntity<KakaoPayReadyRequestDto> request = new HttpEntity<>(kakaoPayReadyRequestDto, this.getHeaders());

            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/ready", request, KakaoPayReadyResponseDto.class);
        }

        public KakaoPayApproveResponseDto kakaoPayApprove(KakaoPayClientApproveRequestDto clientDto,  Long userId){
            Map<String, String> params = new HashMap<>();
            params.put("cid", cid);
            params.put("tid", clientDto.getTid());
            params.put("partner_order_id", "partner_order_id");
            params.put("partner_user_id", "partner_user_id");
            params.put("pg_token", clientDto.getPgToken());

            HttpEntity<Map<String, String>> request = new HttpEntity<>(params, this.getHeaders());

            RestTemplate restTemplate = new RestTemplate();

            KakaoPayApproveResponseDto kakaoPayApproveResponseDto = restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/approve", request, KakaoPayApproveResponseDto.class);

            KakaoPayClientReadyRequestDto clientInfo = (KakaoPayClientReadyRequestDto) redisUtil.getData(userId.toString());

            OrderCreateRequestDto orderCreateRequestDto = OrderCreateRequestDto.builder()
                    .tid(clientDto.getTid())
                    .orderAddr(clientInfo.getOrderAddr())
                    .orderStatus(OrderStatus.COMPLETE)
                    .totalAmount(clientInfo.getTotalAmount())
                    .orderPhone(clientInfo.getOrderPhone())
                    .build();

            orderService.createOrder(orderCreateRequestDto, userId);

            return kakaoPayApproveResponseDto;
        }


        public KakaoPayCancelResponseDto kakaoPayCancel(KakaoPayClientCancelRequestDto kakaoPayClientCancelRequestDto) {
            KakaoPayCancelRequestDto kakaoPayCancelRequestDto = KakaoPayCancelRequestDto.builder()
                    .cid(cid)
                    .tid(orderService.getOrder(kakaoPayClientCancelRequestDto.getOrderId()).getTid())
                    .cancelAmount(kakaoPayClientCancelRequestDto.getCancelAmount())
                    .cancelTaxFreeAmount(0)
                    .build();

            HttpEntity<KakaoPayCancelRequestDto> request = new HttpEntity<>(kakaoPayCancelRequestDto, this.getHeaders());
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.postForObject("https://open-api.kakaopay.com/online/v1/payment/cancel", request, KakaoPayCancelResponseDto.class);
        }

        private HttpHeaders getHeaders() {
            HttpHeaders httpHeaders = new HttpHeaders();

            String auth = "SECRET_KEY " + admin_Key;

            httpHeaders.set("Authorization", auth);
            httpHeaders.set("Content-type", "application/json");

            return httpHeaders;
        }
}

