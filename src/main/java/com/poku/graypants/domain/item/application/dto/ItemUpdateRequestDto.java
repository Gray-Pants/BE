package com.poku.graypants.domain.item.application.dto;

import com.poku.graypants.domain.item.persistence.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class ItemUpdateRequestDto {

    private String itemName;
    private int itemPrice;
    private List<MultipartFile> itemPhotosDto;
    private MultipartFile itemDescImg;
    private int stock;
    private String storeName;
    private Category category;
}
