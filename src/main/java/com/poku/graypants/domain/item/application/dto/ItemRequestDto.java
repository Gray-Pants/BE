package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemRequestDto {

    private String itemName;
    private int itemPrice;
    private List<String> itemPhotosDto;
    private String itemDescImg;
    private int stock;
    private String storeName;
    private Category category;

}
