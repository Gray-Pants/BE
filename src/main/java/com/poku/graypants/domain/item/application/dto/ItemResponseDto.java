package com.poku.graypants.domain.item.application.dto;


import com.poku.graypants.domain.item.persistence.Category;
import com.poku.graypants.domain.item.persistence.Item;
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
    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.itemPrice = item.getItemPrice();
        this.stock = item.getStock();
        this.itemDescImg = item.getItemDescImg();
        this.created_at = item.getCreatedAt();
        this.updated_at = item.getUpdatedAt();
        this.storeName = item.getStore().getStoreName();
        this.categoryTitle = item.getCategory().getTitle();
    }
}
