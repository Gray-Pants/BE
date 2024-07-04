package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.store.persistence.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemCreateDto {

    private String itemName;
    private int itemPrice;
    private List<String> itemPhotosDto;
    private String itemDescImg;
    private Store store;
    private int stock;
    private Category category;

    public Item toEntity(Store store){
        return Item.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                //.itemPhotos(dto.getItemPhotosDto())
                .store(store)
                .stock(stock)
                .itemDescImg(itemDescImg)
                .category(category)
                .build();
    }

}
