package com.poku.graypants.domain.order.persistence;

import com.poku.graypants.domain.order.application.dto.OrderUpdateRequestDto;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import com.siot.IamportRestClient.response.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "order_addr", nullable = false, unique = false, updatable = true, length = 100)
    private String orderAddr;

    @Column(name = "order_post_code", nullable = false, unique = false, updatable = true, length = 5)
    private String orderPostCode;

    @Column(name = "order_phone", nullable = false, unique = false, updatable = true, length = 20)
    private String orderPhone;

    @Column(name = "order_total_price", nullable = true, unique = false, updatable = true)
    private int orderTotalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "imp_uid", nullable = false, unique = false, updatable = false)
    private String impUid;

    @Column(name = "merchant_uid", nullable = false, unique = false, updatable = false)
    private String merchantUid;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "order_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void updateOrder(OrderUpdateRequestDto orderUpdateRequestDto) {
        this.orderAddr = orderUpdateRequestDto.getOrderAddr();
        this.orderPhone = orderUpdateRequestDto.getOrderPhone();
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    public void setOrderTotalPrice(int orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    public Order(Payment payment) {
        this.impUid = payment.getImpUid();
        this.merchantUid = payment.getMerchantUid();
        this.paymentStatus = PaymentStatus.PAID;
    }
}
