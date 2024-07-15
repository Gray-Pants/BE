package com.poku.graypants.domain.store.application;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.item.application.ItemDataService;
import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.orderItem.application.OrderItemDataService;
import com.poku.graypants.domain.orderItem.application.OrderItemService;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.domain.store.persistence.StoreRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ItemDataService itemDataService;
    private final OrderItemDataService orderItemDataService;

    public Store getStoreByEmail(String email) {
        return getVerifyStore(email);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElseThrow(
                () -> new GrayPantsException(ExceptionStatus.STORE_NOT_FOUND)
        );
    }

    public Store getVerifyStore(String email) {
        return storeRepository.findByStoreEmail(email)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.STORE_NOT_FOUND));
    }

    public EmailAuthenticateAble saveStore(String email, String name, String password) {
        if(storeRepository.findByStoreEmail(email).isPresent()) {
            throw new GrayPantsException(ExceptionStatus.DUPLICATED_EMAIL);
        }
        return storeRepository.save(Store.builder()
                .storeEmail(email)
                .storeName(name)
                .storePassword(password)
                .build());
    }

    public List<ItemResponseDto> getItemByStoreId(Long storeId) {
        List<Item> items = itemDataService.getItemsByStoreId(storeId);

        return items.stream()
                .map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

    public MyOrderItemsResponseDto getMyOrderItems(Long storeId) {
        List<OrderItem> orderItems = orderItemDataService.getSortedByDateOrderItemsByStoreId(storeId);
        Map<String,List<OrderItemResponseDto>> map = new HashMap<>();
        for(OrderItem orderItem : orderItems) {
            String date = orderItem.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if(map.containsKey(date)) {
                map.get(date).add(new OrderItemResponseDto(orderItem));
            } else {
                map.put(date, new ArrayList<>(List.of(new OrderItemResponseDto(orderItem))));
            }
        }
        return new MyOrderItemsResponseDto(map);
    }
}
