package com.poku.graypants.domain.order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PaymentData {
  private String imp_uid;
  private String pg = "uplus"; // 고정값
  private String merchant_uid; // 가맹점 주문번호
  private String name;
  private String pay_method = "card"; // 고정값
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
  private String currency = "KRW"; // 고정값
  private String locale = "ko"; // 고정값
  private String custom_data; // json 형태로 변환 필요

  private Display display; // DisplayOptionsDto -> Display
  private boolean appCard = false;
  private boolean useCardPoint = false;

  private BypassOptions bypass; // BypassOptionsDto -> Bypass

  private String card_number; // 카드 번호
  private String expiry; // 카드 유효기간
  private String birth; // 생년월일 또는 사업자등록번호
  private String pwd_2digit; // 카드 비밀번호 앞 2자리(법인카드만 해당)

  @Getter
  @Setter
  public static class Display {
    @JsonProperty("card_quota")
    private List<Integer> cardQuota;
    @JsonProperty("only_installment")
    private boolean onlyInstallment = true;

    public Display(List<Integer> cardQuota, boolean onlyInstallment) {
      this.cardQuota = cardQuota;
      this.onlyInstallment = onlyInstallment;
    }
  }

  @Getter
  @Setter
  public static class BypassOptions { // 클래스 이름 변경
    private TossPayments tosspayments; // 타입 확인

    public BypassOptions(TossPayments tossPayments) {
      this.tosspayments = tossPayments;
    }
  }

  // TossPayments 클래스를 BypassOptions와 같은 레벨로 이동
  @Getter
  @Setter
  public static class TossPayments {
    @JsonProperty("useInternationalCardOnly")
    private boolean useInternationalCardOnly = false;
    public TossPayments(boolean useInternationalCardOnly) {
      this.useInternationalCardOnly = useInternationalCardOnly;
    }
  }

  // PaymentRequestDto를 기반으로 PaymentData 생성
  public PaymentData(PaymentRequestDto requestDto) {
    this.imp_uid = requestDto.getImp_uid();
    this.pg = requestDto.getPg();
    this.merchant_uid = requestDto.getMerchant_uid();
    this.name = requestDto.getName();
    this.amount = requestDto.getAmount();
    this.buyer_email = requestDto.getBuyer_email();
    this.buyer_name = requestDto.getBuyer_name();
    this.buyer_tel = requestDto.getBuyer_tel();
    this.buyer_addr = requestDto.getBuyer_addr();
    this.buyer_postcode = requestDto.getBuyer_postcode();
    this.m_redirect_url = requestDto.getM_redirect_url();
    this.notice_url = requestDto.getNotice_url();
    this.confirm_url = requestDto.getConfirm_url();
    this.custom_data = requestDto.getCustom_data().toString();

    this.display = new Display(
            requestDto.getDisplay().getCardQuota(),
            requestDto.getDisplay().isOnlyInstallment()
    );
    this.bypass = new BypassOptions( // 수정된 BypassOptions 생성자 사용
            new TossPayments(
                    requestDto.getBypass().getTosspayments().isUseInternationalCardOnly()
            )
    );


    // orderItems를 사용하여 추가 정보 설정
    for (OrderItemResponseDto orderItemDto : requestDto.getOrderItems()) {
      if (this.card_number == null) {
        this.card_number = orderItemDto.getCardNumber(); // 첫 번째 상품의 카드 번호 사용
        this.expiry = orderItemDto.getExpiry(); // 첫 번째 상품의 카드 유효기간 사용
      }
    }
  }


}
