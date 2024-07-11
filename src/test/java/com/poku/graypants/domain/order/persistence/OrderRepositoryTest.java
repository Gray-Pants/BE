package com.poku.graypants.domain.order.persistence;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@DataJpaTest
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  @DisplayName("주문 생성 테스트")
  void createOrder() {
    // given
    Order order = Order.builder()
            .orderId(1L)
            .orderAddr("서울시 강남구")
            .orderPhone("010-1234-5678")
            .build();

    // when
    Order savedOrder = orderRepository.save(order);

    // then
    assertEquals(order, savedOrder);
  }
}