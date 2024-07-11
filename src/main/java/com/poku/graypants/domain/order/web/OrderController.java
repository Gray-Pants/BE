package com.poku.graypants.domain.order.web;

import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

/**
 * 주문 컨트롤러
 * @version 1.0.0
 * @see OrderService
 * @Author Jgone2
 * @Since 2021-07-05
 * 컨트롤러 레벨에서 예외처리 핸들러를 추가해도 좋을듯
 */
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/create")
  public ResponseEntity<ApiResult<OrderResponseDto>> createOrder(@RequestBody @Valid OrderCreateRequestDto orderCreateRequestDto,
                                                                 @AuthenticationPrincipal Long userId) {
    OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateRequestDto, userId);
    return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.CREATED);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<ApiResult<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
    OrderResponseDto orderResponseDto = orderService.getOrder(orderId);
    return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<ApiResult<List<OrderResponseDto>>> getOrders(@AuthenticationPrincipal Long userId) {
    List<OrderResponseDto> orderResponseDtoList = orderService.getOrders(userId);
    return new ResponseEntity<>(success(orderResponseDtoList), new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<ApiResult<OrderResponseDto>> updateOrder(@PathVariable Long orderId,
                                                                 @RequestBody @Valid OrderUpdateRequestDto orderUpdateRequestDto,
                                                                 @AuthenticationPrincipal Long userId) {
    OrderResponseDto orderResponseDto = orderService.updateOrder(orderId, orderUpdateRequestDto, userId);
    return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<ApiResult<OrderResponseDto>> deleteOrder(@PathVariable Long orderId,
                                                                 @AuthenticationPrincipal Long userId) {
    OrderResponseDto orderResponseDto = orderService.deleteOrder(orderId, userId);
    return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.NO_CONTENT);
  }
}
