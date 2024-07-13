package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.application.dto.PaymentData;
import com.poku.graypants.domain.order.application.dto.PaymentRequestDto;
import com.poku.graypants.domain.order.application.dto.PaymentResult;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.HttpException;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class IamportService {

  private final IamportClient iamportClient;
  private final String impUid;


  public PaymentResult requestPayment(PaymentRequestDto paymentRequestDto) {
    try {
      // 1. 결제 요청 데이터 생성
      paymentRequestDto.setImp_uid(impUid);
      PaymentData paymentData = new PaymentData(paymentRequestDto);

      // 2. 아임포트 API 호출
      IamportResponse<Payment> response = iamportClient.paymentByImpUid(paymentData.getImp_uid());

      // 3. 결제 결과 처리
      if (response.getCode() == 0) {
        Payment payment = response.getResponse();
        return new PaymentResult(payment);
      } else {
        throw new RuntimeException("결제 실패: " + response.getMessage());
      }
    } catch (IamportResponseException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
