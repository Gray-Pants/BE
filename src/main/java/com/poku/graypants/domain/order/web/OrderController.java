package com.poku.graypants.domain.order.web;

import com.poku.graypants.domain.order.application.OrderService;
import com.poku.graypants.domain.order.application.OrderItemService;
import com.poku.graypants.domain.order.application.OrderServiceImpl;
import com.poku.graypants.domain.order.application.dto.*;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.jwt.JwtProvider;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

/**
 * 주문 컨트롤러
 *
 * @version 1.0.0
 * @see OrderService
 * @Author Jgone2
 * @Since 2021-07-05
 * 컨트롤러 레벨에서 예외처리 핸들러를 추가해도 좋을 듯
 */
@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderServiceImpl orderService;
  private final OrderItemService orderItemService;
  private final UserService userService;
  private final JwtProvider jwtTokenProvider;

  @PostMapping
  public ResponseEntity<ApiResult<OrderResponseDto>> createOrder(
          @RequestBody @Valid OrderCreateRequestDto orderCreateRequestDto,
          @AuthenticationPrincipal Long userId) {

    User findUser = userService.getUser(userId);
    String jwtToken = jwtTokenProvider.generateToken(findUser, Duration.ofMinutes(30));

    OrderResponseDto createResponseDto = new OrderResponseDto(orderCreateRequestDto.toEntity(findUser), jwtToken);
    return new ResponseEntity<>(success(createResponseDto), new HttpHeaders(), HttpStatus.ACCEPTED);
  }

  @PostMapping("/{orderId}/items")
  public ResponseEntity<ApiResult<List<OrderItemResponseDto>>> createOrderItem(
          @PathVariable Long orderId,
          @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
          @RequestBody @Valid List<OrderItemCreateRequestDto> orderItemCreateRequestDtos) {

    // JWT 토큰 검증 (생략)
    Authentication userId = jwtTokenProvider.getAuthentication(authorizationHeader.replace("Bearer ", ""));
    Order order = orderService.getVerifyExistsOrder(orderId);

    List<OrderItemResponseDto> orderItemResponseDtoList = orderItemService.createOrderItems(order, userId, orderItemCreateRequestDtos);
    return ResponseEntity.status(HttpStatus.CREATED).body(success(orderItemResponseDtoList));
  }

  @PostMapping("/{orderId}/payments") // 결제 엔드포인트 추가
  public ResponseEntity<ApiResult<String>> createPayment(
          @PathVariable Long orderId,
          @RequestBody @Valid PaymentRequestDto paymentRequestDto,
          @AuthenticationPrincipal Long userId
  ) {
    String impUid = orderService.createPayment(orderId, paymentRequestDto, userId);
    return ResponseEntity.ok(success(impUid));
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<ApiResult<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
    OrderResponseDto orderResponseDto = orderService.getOrder(orderId);
    return new ResponseEntity<>(success(orderResponseDto), new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<ApiResult<List<OrderResponseDto>>> getOrders(@AuthenticationPrincipal Long userId) {
    List<OrderResponseDto> orderResponseDtoList = orderService.getOrders(userId);
    return new ResponseEntity<>(success(orderResponseDtoList), new HttpHeaders(), HttpStatus.OK);
  }

  @PutMapping("/{orderId}")
  public ResponseEntity<ApiResult<Void>> updateOrder(@PathVariable Long orderId,
                                                     @RequestBody @Valid OrderUpdateRequestDto orderUpdateRequestDto,
                                                     @AuthenticationPrincipal Long userId) {
    orderService.updateOrder(orderId, orderUpdateRequestDto, userId);
    return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{orderId}")
  public ResponseEntity<ApiResult<Void>> deleteOrder(@PathVariable Long orderId, @AuthenticationPrincipal Long userId) {
    orderService.deleteOrder(orderId, userId);
    return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
  }
}