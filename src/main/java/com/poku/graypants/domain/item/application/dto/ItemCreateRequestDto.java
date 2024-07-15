package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.store.persistence.Store;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemCreateRequestDto {

    private String itemName;
    private int itemPrice;
    private List<MultipartFile> itemPhotos;
    private MultipartFile itemDescImg;
    private int stock;
    private Category category;


    public Item toEntity(){
        return Item.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .stock(stock)
                .category(category)
                .build();
    }


}
