package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import lombok.*;

import java.util.List;

@Getter
public class ItemRequestDto {

    private String itemName;
    private int itemPrice;
    private List<String> itemPhotosDto;
    private String itemDescImg;
    private int stock;
    private String storeName;
    private Category category;

    @Builder
    public ItemRequestDto(String itemName, int itemPrice, List<String> itemPhotosDto, String itemDescImg,
                          int stock, String storeName, Category category) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemPhotosDto = itemPhotosDto;
        this.itemDescImg = itemDescImg;
        this.stock = stock;
        this.storeName = storeName;
        this.category = category;
    }
}
