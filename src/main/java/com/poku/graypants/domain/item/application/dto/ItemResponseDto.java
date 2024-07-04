package com.poku.graypants.domain.item.application.dto;


import com.poku.graypants.domain.item.persistence.Category;
import com.poku.graypants.domain.store.persistence.Store;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private int itemPrice;
    private int stock;
    private String itemDescImg;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String storeName;
    private String categoryTitle;

    @Builder
    @QueryProjection
    public ItemResponseDto(Long id, String itemName, int itemPrice, int stock,
                           String itemDescImg, LocalDateTime created_at, LocalDateTime updated_at,
                           String storeName, String categoryTitle) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stock = stock;
        this.itemDescImg = itemDescImg;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.storeName = storeName;
        this.categoryTitle = categoryTitle;
    }
}
