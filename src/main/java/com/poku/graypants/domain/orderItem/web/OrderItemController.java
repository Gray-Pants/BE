package com.poku.graypants.domain.orderItem.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.orderItem.application.OrderItemService;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("/{orderItemId}")
    public ResponseEntity<ApiResult<OrderItemResponseDto>> getOrderItem(@PathVariable Long orderItemId,
                                                                        @AuthenticationPrincipal Long userId) {
        OrderItemResponseDto response = orderItemService.getVerifyOrderItemByOrderItemIdWithUserId(orderItemId, userId);
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }
}
