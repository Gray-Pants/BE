package com.poku.graypants.domain.cart.application.dto;

import com.poku.graypants.domain.cart.persistence.CartItem;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CartItemResponseDto {
    private Long cartItemId;
    private int cartItemQuantity;
    private Long userId;
    private ItemResponseDto item;

    public CartItemResponseDto(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.cartItemQuantity = cartItem.getCartItemQuantity();
        this.userId = cartItem.getUser().getUserId();
        this.item = new ItemResponseDto(cartItem.getItem());
    }
}
