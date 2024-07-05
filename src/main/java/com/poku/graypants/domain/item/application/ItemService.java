package com.poku.graypants.domain.item.application;

import com.poku.graypants.domain.item.application.dto.*;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemRepositoryCustom;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemRepositoryCustom itemRepositoryCustom;


    public ItemResponseDto createItem(ItemCreateDto itemCreateDto) {
        // USER email 을 통한 STORE 받아오기
        // authenticator.
        //return new ItemResponseDto(itemRepository.save(itemCreateDto.toEntity()));
        return null;
    }

    public ItemResponseDto updateItem(Long id, ItemUpdateDto itemUpdateDto) {
        Item item = getItemByID(id);

        return new ItemResponseDto(item.updateItem(itemUpdateDto));
    }


    public List<ItemResponseDto> findByNameAll(String name) {
        return itemRepositoryCustom.searchItemList(name);
    }

    public ItemResponseDto findById(Long id) {
        Item item = getItemByID(id);
        return new ItemResponseDto(item);
    }


    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item getItemByID(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
                new GrayPantsException(ExceptionStatus.ITEM_NOT_FOUND));
        return item;
    }

}
