package com.poku.graypants.domain.orderItem.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemUpdateRequestDto;
import com.poku.graypants.domain.review.persistence.Review;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Table(name = "ORDER_ITEMS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTime {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "order_item_id", nullable = false, unique = true, updatable = false, insertable = false)
  private Long orderItemId;

  @Column(name = "order_item_price", nullable = false)
  private int orderItemPrice;

  @Column(name = "order_item_quantity", nullable = false, unique = false, updatable = true, insertable = true)
  private int orderItemQuantity;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_item_status", nullable = false, unique = false, updatable = true, insertable = true)
  private OrderItemStatus orderItemStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

  @OneToOne(mappedBy = "orderItem", fetch = FetchType.LAZY)
  private Review review;


  public void updateOrderItemStatus(OrderItemUpdateRequestDto orderItemUpdateRequestDto) {
    this.orderItemStatus = orderItemUpdateRequestDto.getOrderItemStatus();
  }

  public void updateReview(Review review) {
    this.review = review;
  }

  @Builder
  public OrderItem(int orderItemPrice, int orderItemQuantity, OrderItemStatus orderItemStatus, Order order, Item item, Store store, Review review) {
    this.orderItemPrice = orderItemPrice;
    this.orderItemQuantity = orderItemQuantity;
    this.orderItemStatus = orderItemStatus;
    this.order = order;
    this.item = item;
  }
}
