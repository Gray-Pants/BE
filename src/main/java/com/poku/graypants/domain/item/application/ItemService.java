package com.poku.graypants.domain.item.application;

import com.poku.graypants.domain.item.application.dto.*;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import com.poku.graypants.domain.item.persistence.QItem;
import com.poku.graypants.domain.store.persistence.StoreRepository;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory query;

    public ItemResponseDto createItem(Authenticator authenticator, ItemCreateDto itemCreateDto) {
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
        query = new JPAQueryFactory(em);
        QItem item = QItem.item;
         List<ItemResponseDto> searchItemList = query.
            select(new QItemResponseDto(item.Id, item.itemName, item.itemPrice, item.stock, item.itemDescImg, item.created_at, item.updated_at, item.store.storeName, item.category.categoryName))
                .from(item)
                .where(item.itemName.contains(name)
                        .or(item.store.storeName.contains(name)))
                .fetch();
         return searchItemList;
    }

    public ItemResponseDto findById(Long id) {
        Item item = getItemByID(id);
        return new ItemResponseDto(item);
    }


    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private Item getItemByID(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
            {throw new RuntimeException(ExceptionStatus.ITEM_NOT_FOUND.getMessage());
            });
        return item;
    }
}
