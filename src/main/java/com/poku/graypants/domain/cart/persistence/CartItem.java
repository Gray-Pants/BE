package com.poku.graypants.domain.cart.persistence;

import com.poku.graypants.domain.item.persistence.Item;
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
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id",unique = true, nullable = false)
    private Long cartItemId;

    @Column(name = "cart_item_quantity")
    private int cartItemQuantity;

    @CreatedDate
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_Id")
    private Item item;

    @Builder
    public CartItem(int cartItemQuantity, Cart cart, Item item) {
        this.cartItemQuantity = cartItemQuantity;
        this.cart = cart;
        this.item = item;
    }
}
