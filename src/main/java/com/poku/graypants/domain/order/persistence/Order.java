package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Builder
@Table(name = "ORDERS")
@EqualsAndHashCode(exclude = "orderId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long orderId;

    @Column(name = "order_addr", nullable = false, unique = false, updatable = true, length = 100)
    private String orderAddr;

    @Column(name = "order_phone", nullable = false, unique = false, updatable = true, length = 20)
    private String orderPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public void updateOrder(OrderUpdateRequestDto orderUpdateRequestDto) {
        this.orderAddr = orderUpdateRequestDto.getOrderAddr();
        this.orderPhone = orderUpdateRequestDto.getOrderPhone();
    }
}
