package com.poku.graypants.domain.item.application;

import com.poku.graypants.domain.item.application.dto.*;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemRepositoryCustom;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositoryCustom itemRepositoryCustom;


    public ItemResponseDto createItem(ItemCreateRequestDto itemCreateRequestDto) {
        // USER email 을 통한 STORE 받아오기
        // authenticator.
        //return new ItemResponseDto(itemRepository.save(itemCreateDto.toEntity()));
        return null;
    }

    public ItemResponseDto updateItem(Long id, ItemUpdateRequestDto itemUpdateRequestDto) {
        Item item = getItemByID(id);

        return new ItemResponseDto(item.updateItem(itemUpdateRequestDto));
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

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ITEM_NOT_FOUND));
    }
}
