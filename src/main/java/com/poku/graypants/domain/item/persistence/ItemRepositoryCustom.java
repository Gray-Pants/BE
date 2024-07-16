package com.poku.graypants.domain.item.persistence;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryCustom {

    List<ItemResponseDto> searchItemList(String name);
}
