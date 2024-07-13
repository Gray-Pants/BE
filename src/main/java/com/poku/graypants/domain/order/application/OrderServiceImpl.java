package com.poku.graypants.domain.order.application;

import com.poku.graypants.domain.order.application.dto.*;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.order.persistence.OrderRepository;
import com.poku.graypants.domain.order.persistence.PaymentStatus;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.RequiredArgsConstructor;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 주문 서비스 구현체
 * @version 1.0.0
 * @see OrderService
 * @Author Jgone2
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserService userService;
  private final SessionRepository<?> sessionRepository;
  private final IamportService iamportService;

  @Override
  @Transactional
  public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto, Long userId) {
    User findUser = userService.getUser(userId);
    Order findOrder = orderCreateRequestDto.toEntity(findUser);

    // ... (재고 확인, 배송비 계산 등 비즈니스 로직 추가)
    verifyOrderUserAndLoginUserMatch(findOrder, findUser);

    Order savedOrder = orderRepository.save(findOrder);
    return new OrderResponseDto(savedOrder);
  }

  @Override
  @Transactional
  public OrderResponseDto completeOrder(Long orderId, PaymentRequestDto paymentRequestDto) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_NOT_FOUND));

    // 아임포트 API 호출 및 결제 처리
    PaymentResult resultPayment = iamportService.requestPayment(paymentRequestDto);

    // 결제 정보를 Order 엔티티에 저장
    order.setPaymentStatus(PaymentStatus.PAID); // 결제 상태 업데이트

    return new OrderResponseDto(order);
  }

  @Override
  @Transactional
  public String createPayment(Long orderId, PaymentRequestDto paymentRequestDto, Long userId) {
    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_NOT_FOUND));

    verifyOrderUserAndLoginUserMatch(order, userService.getUser(userId));

    // 아임포트 API 호출 및 결제 처리
    PaymentResult payment = iamportService.requestPayment(paymentRequestDto);

    // 결제 정보를 Order 엔티티에 저장
    order.setPaymentStatus(PaymentStatus.READY); // 결제 상태 업데이트
    String tempMerchantUid = LocalDateTime.now() + UUID.randomUUID().toString();
    order.setMerchantUid(tempMerchantUid);
    order.setImpUid(payment.getImpUid());
    orderRepository.save(order);

    return payment.toString();
  }

  @Override
  @Transactional(readOnly = true)
  public OrderResponseDto getOrder(Long orderId) {
    return new OrderResponseDto(getVerifyExistsOrder(orderId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderResponseDto> getOrders(Long userId) {
    List<Order> findAllOrders = orderRepository.findAllByUser(userService.getUser(userId));
    return findAllOrders.stream()
            .map(OrderResponseDto::new)
            .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public OrderResponseDto updateOrder(Long orderId, OrderUpdateRequestDto orderUpdateRequestDto, Long userId) {
    User findUser = userService.getUser(userId);
    Order findOrder = getVerifyExistsOrder(orderId);

    verifyOrderUserAndLoginUserMatch(findOrder, findUser);

    findOrder.updateOrder(orderUpdateRequestDto);
    return new OrderResponseDto(findOrder);
  }

  /**
   * 주문 삭제
   * @param orderId
   * @return void
   * @exception GrayPantsException 주문이 존재하지 않을 경우
   * 개선 사항 -> 추후 하드삭제가 아닌 소프트 삭제로 변경(삭제여부 컬럼 추가)
   */
  @Override
  @Transactional
  public void deleteOrder(Long orderId, Long userId) {
    Order verifyExistsOrder = getVerifyExistsOrder(orderId);
    User findUser = userService.getUser(userId);
    verifyOrderUserAndLoginUserMatch(verifyExistsOrder, findUser);
    orderRepository.delete(verifyExistsOrder);
  }

  @Transactional(readOnly = true)
  public Order getVerifyExistsOrder(Long orderId) {
    return orderRepository.findById(orderId).orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_NOT_FOUND));
  }

  public void verifyOrderUserAndLoginUserMatch(Order findOrder, User findUser) {
    if (!findOrder.getUserId().equals(findUser.getUserId())) {
      throw new GrayPantsException(ExceptionStatus.ORDER_AND_USER_MISMATCH);
    }
  }
}
