package com.poku.graypants.domain.item.application;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.item.persistence.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDataService {

    private final ItemRepository itemRepository;

    public List<Item> getItemsByStoreId(Long storeId) {
        return itemRepository.findByStore_StoreId(storeId);
    }
}
