package com.poku.graypants.domain.order.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 주문 컨트롤러
 *
 * @version 1.0.0
 * @Author Jgone2
 * @Since 2021-07-05 컨트롤러 레벨에서 예외처리 핸들러를 추가해도 좋을듯
 * @see OrderService
 */
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResult<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrder(orderId);
        return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResult<Void>> updateOrder(@PathVariable Long orderId,
                                                       @RequestBody @Valid OrderUpdateRequestDto orderUpdateRequestDto,
                                                       @AuthenticationPrincipal Long userId) {
        orderService.updateOrder(orderId, orderUpdateRequestDto, userId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<ApiResult<Void>> deleteOrder(@PathVariable Long orderId,
                                                       @AuthenticationPrincipal Long userId) {
        orderService.deleteOrder(orderId, userId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }
}
