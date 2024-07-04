package com.poku.graypants.domain.item.application;

import com.poku.graypants.domain.item.application.dto.ItemRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.QItemResponseDto;
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

    public ItemResponseDto createItem(ItemRequestDto itemRequestDto) {
        Item item = toEntity(itemRequestDto);
        return toDto(itemRepository.save(item));
    }

    public ItemResponseDto updateItem(Long id, ItemRequestDto itemRequestDto) {
        Item item = itemRepository.findById(id).orElseThrow(() ->
            {throw new RuntimeException(ExceptionStatus.ITEM_NOT_FOUND.getMessage());
            });
        item.updateItem(itemRequestDto);
        return toDto(itemRepository.save(item));
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
        Item item = itemRepository.findById(id).orElseThrow(() ->
        {throw new RuntimeException(ExceptionStatus.ITEM_NOT_FOUND.getMessage());
        });
        return toDto(item);
    }


    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private Item toEntity(ItemRequestDto dto){
        return Item.builder()
                .itemName(dto.getItemName())
                .itemPrice(dto.getItemPrice())
                //.itemPhotos(dto.getItemPhotosDto())
                .store(storeRepository.findByStoreName(dto.getStoreName()))
                .stock(dto.getStock())
                .itemDescImg(dto.getItemDescImg())
                .category(dto.getCategory())
                .build();

    }

    private ItemResponseDto toDto(Item entity) {
        return ItemResponseDto.builder()
                .id(entity.getId())
                .itemName(entity.getItemName())
                .itemPrice(entity.getItemPrice())
                .stock(entity.getStock())
                .itemDescImg(entity.getItemDescImg())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .storeName(entity.getStore().getStoreName())
                .categoryTitle(entity.getCategory().getTitle())
                .build();
    }

}
