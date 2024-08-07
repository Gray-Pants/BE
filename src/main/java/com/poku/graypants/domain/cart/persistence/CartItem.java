package com.poku.graypants.domain.cart.persistence;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "CART_ITEMS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id",unique = true, nullable = false)
    private Long cartItemId;

    @Column(name = "cart_item_quantity")
    private int cartItemQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_Id")
    private Item item;

    @Builder
    public CartItem(int cartItemQuantity, User user, Item item) {
        this.cartItemQuantity = cartItemQuantity;
        this.user = user;
        this.item = item;
    }
    //==비즈니스 로직==//
    public void updateQuantity(int newQuantity) {
        this.cartItemQuantity = newQuantity;
    }
}
