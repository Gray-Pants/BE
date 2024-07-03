package com.poku.graypants.domain.order.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`ORDERS`")
public class Order {
    @Id
    @Column(name = "order_id")
    private Long orderId;
}
