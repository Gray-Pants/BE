package com.poku.graypants.domain.orderItem.application;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.order.application.OrderServiceImpl;
import com.poku.graypants.domain.order.application.dto.OrderCreateRequestDto;
import com.poku.graypants.domain.order.application.dto.OrderResponseDto;
import com.poku.graypants.domain.order.persistence.Order;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemCreateRequestDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemResponseDto;
import com.poku.graypants.domain.orderItem.application.dto.OrderItemUpdateRequestDto;
import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import com.poku.graypants.domain.orderItem.persistence.OrderItemRepository;
import com.poku.graypants.domain.orderItem.persistence.OrderItemStatus;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(Order order, Item item, int orderItemQuantity, OrderItemStatus orderItemStatus) {

        return orderItemRepository.save(OrderItem.builder()
                .store(item.getStore())
                .order(order)
                .item(item)
                .orderItemPrice(item.getItemPrice())
                .orderItemQuantity(orderItemQuantity)
                .orderItemStatus(orderItemStatus)
                .build());
    }

    @Override
    public OrderItemResponseDto updateOrderItemStatus(Long orderItemId, OrderItemUpdateRequestDto orderItemUpdateRequestDto) {
        OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
        verifyOrderItem.updateOrderItemStatus(orderItemUpdateRequestDto);
        return new OrderItemResponseDto(verifyOrderItem);
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        OrderItem verifyOrderItem = getVerifyOrderItem(orderItemId);
        orderItemRepository.delete(verifyOrderItem);
    }

    @Override
    public OrderItem getVerifyOrderItemByOrderItemId(Long orderItemId) {
        return orderItemRepository.findByOrderItemId(orderItemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_ITEM_NOT_FOUND));
    }

    @Override
    public OrderItemResponseDto getVerifyOrderItemByOrderItemIdWithUserId(Long orderItemId, Long userId) {
        OrderItem orderItem = getVerifyOrderItem(orderItemId);
        if (!orderItem.getOrder().getUser().getUserId().equals(userId)) {
            throw new GrayPantsException(ExceptionStatus.ORDER_ITEM_FORBIDDEN);
        }
        return new OrderItemResponseDto(orderItem);
    }



    private OrderItem getVerifyOrderItem(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.ORDER_ITEM_NOT_FOUND));
    }
}
