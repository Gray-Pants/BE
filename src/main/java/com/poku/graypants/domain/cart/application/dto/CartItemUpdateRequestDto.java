package com.poku.graypants.domain.cart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemUpdateRequestDto {
    private Long cartItemId;
    private int newQuantity;
}