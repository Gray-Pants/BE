package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Column(name = "order_item_price", nullable = false)
    private int orderItemPrice;

    @Column(name = "order_item_quantity", nullable = false)
    private int orderItemQuantity;

    @Enumerated(EnumType.STRING)
    private OrderItemStatus orderItemStatus;

    @Column(name = "order_item_addr", nullable = false, length = 10)
    private String orderItemAddr;

    @Column(name = "order_item_phone", nullable = false, length = 20)
    private String orderItemPhone;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public OrderItem(int orderItemPrice, int orderItemQuantity, OrderItemStatus orderItemStatus, String orderItemAddr, String orderItemPhone, Order order, Item item, Store store) {
        super();
        this.orderItemPrice = orderItemPrice;
        this.orderItemQuantity = orderItemQuantity;
        this.orderItemStatus = orderItemStatus;
        this.orderItemAddr = orderItemAddr;
        this.orderItemPhone = orderItemPhone;
        this.order = order;
        this.item = item;
        this.store = store;
    }
}
