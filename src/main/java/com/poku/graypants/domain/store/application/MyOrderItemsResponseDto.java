package com.poku.graypants.domain.store.application;

import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyOrderItemsResponseDto {
    private Map<String, List<OrderItemResponseDto>> orderItems;
}
