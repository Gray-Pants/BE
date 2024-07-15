package com.poku.graypants.domain.payments.persistence.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayReadyResponseDto {

    //결제 고유 번호, 20자
    private String tid;
    private String nextRedirectMobileUrl;
    private String nextRedirectPcUrl;

    private LocalDateTime created_at;
}
