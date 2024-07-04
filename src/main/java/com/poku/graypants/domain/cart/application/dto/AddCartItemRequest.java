package com.poku.graypants.domain.cart.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddCartItemRequest {
    private Long itemId;
    private int quantity;
}

