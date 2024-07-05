package com.poku.graypants.domain.cart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemAddRequestDto {
    private Long itemId;
    private int quantity;
}

