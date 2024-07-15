package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Builder
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long orderId;

    @Column(name = "tid", nullable = false)
    private String tid;

    @Column(name = "order_addr", nullable = false, unique = false, updatable = true, length = 100)
    private String orderAddr;

    @Column(name = "order_phone", nullable = false, unique = false, updatable = true, length = 20)
    private String orderPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    public void updateOrder(OrderUpdateRequestDto orderUpdateRequestDto) {
        this.orderAddr = orderUpdateRequestDto.getOrderAddr();
        this.orderPhone = orderUpdateRequestDto.getOrderPhone();
    }
}
