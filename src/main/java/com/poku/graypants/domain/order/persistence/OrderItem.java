package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.dto.OrderItemUpdateRequestDto;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "ORDER_ITEMS")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class OrderItem extends BaseTime {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "order_item_id", nullable = false, unique = true, updatable = false, insertable = false)
  private Long orderItemId;

  @Column(name = "order_item_price", nullable = false, unique = false, updatable = true, insertable = false)
  private int orderItemPrice;

  @Column(name = "order_item_quantity", nullable = false, unique = false, updatable = true, insertable = true)
  private int orderItemQuantity;

  @Column(name = "order_item_option", nullable = false, unique = false, updatable = true, insertable = true)
  private String orderItemOption;

  @Column(name = "review", nullable = false, unique = false, updatable = true, insertable = true)
  @Builder.Default
  private Boolean review = false; // 리뷰 작성 여부

  @Enumerated(EnumType.STRING)
  @Column(name = "order_item_status", nullable = false, unique = false, updatable = true, insertable = true)
  private OrderItemStatus orderItemStatus;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "expiry")
  private String expiry;

  @Column(name = "birth")
  private String birth;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;


  public void updateOrderItem(OrderItemUpdateRequestDto orderItemUpdateRequestDto) {
    this.orderItemQuantity = orderItemUpdateRequestDto.getOrderItemQuantity();
    this.orderItemStatus = orderItemUpdateRequestDto.getOrderItemStatus();
  }

  public void updateReview(Boolean review) {
    this.review = review;
  }
}
