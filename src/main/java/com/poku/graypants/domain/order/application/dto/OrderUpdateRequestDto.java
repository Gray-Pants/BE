package com.poku.graypants.domain.order.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderUpdateRequestDto {

      private String orderAddr;
      private String orderPhone;
}
