package com.poku.graypants.domain.order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PaymentRequestDto {
  private String imp_uid;
  private String pg = "uplus";
  private String merchant_uid; // 각 주문(Order)별로 유니크한 값 (주문번호 등)
  private String name;
  private String pay_method = "card"; // "card"로 고정 (카드 결제만 지원)
  private boolean escrow = true;
  private BigDecimal amount;

  private String buyer_name;
  private String buyer_email;
  private String buyer_tel;
  private String buyer_addr;
  private String buyer_postcode;

  private String m_redirect_url; // 필수
  private String notice_url;
  private String confirm_url;

  private String currency = "KRW";
  private String locale = "ko";

  @JsonProperty("custom_data")
  private CustomDataDto custom_data; // 사용자 정보

  private DisplayOptionsDto display;
  private boolean appCard = false;
  private boolean useCardPoint = false;

  private BypassOptionsDto bypass;

  @JsonProperty("order_items")
  private List<OrderItemResponseDto> orderItems = new ArrayList<>();

  @Getter
  @Setter
  public static class CustomDataDto {
    private Long userId;

    public CustomDataDto(Long userId) {
      this.userId = userId;
    }
  }

  @Getter
  @Setter
  public static class DisplayOptionsDto {
    @JsonProperty("card_quota")
    private List<Integer> cardQuota;
    @JsonProperty("only_installment")
    private boolean onlyInstallment = true;
  }

  @Getter
  @Setter
  public static class BypassOptionsDto {
    private TossPaymentsDto tosspayments;
  }

  @Getter
  @Setter
  public static class TossPaymentsDto {
    @JsonProperty("useInternationalCardOnly")
    private boolean useInternationalCardOnly = false;
  }
}
