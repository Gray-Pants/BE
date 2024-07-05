package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`ORDERS`")
public class Order extends BaseTime {
    @Id
    @Column(name = "order_id")
    private Long orderId;
}
