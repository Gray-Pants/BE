package com.poku.graypants.domain.item.application.dto;


import com.poku.graypants.domain.item.persistence.Item;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ItemResponseDto {

    private Long itemId;
    private String itemName;
    private int itemPrice;
    private int stock;
    private String itemDescImg;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String storeName;
    private String categoryTitle;
    private List<String> itemPhotos;

    @Builder
    @QueryProjection
    public ItemResponseDto(Item item) {
        this.itemId = item.getItemId();
        this.itemName = item.getItemName();
        this.itemPrice = item.getItemPrice();
        this.stock = item.getStock();
        this.itemDescImg = item.getItemDescImg();
        this.created_at = item.getCreatedAt();
        this.updated_at = item.getUpdatedAt();
        this.storeName = item.getStore().getStoreName();
        this.categoryTitle = item.getCategory().getTitle();

        this.itemPhotos = photosStringToList(item);
    }

    private static List<String> photosStringToList(Item item) {
        String[] arr = item.getItemPhotos().split(",");

        return new ArrayList<>(Arrays.asList(arr));
    }
}
