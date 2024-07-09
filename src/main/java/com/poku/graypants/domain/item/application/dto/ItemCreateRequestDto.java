package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.store.persistence.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemCreateRequestDto {

    private String itemName;
    private int itemPrice;
    private List<MultipartFile> itemPhotos;
    private MultipartFile itemDescImg;
    private Store store;
    private int stock;
    private Category category;


    public Item toEntity(){
        return Item.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                //.store(store)
                .stock(stock)
                .category(category)
                .build();
    }

}
