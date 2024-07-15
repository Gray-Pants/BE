package com.poku.graypants.domain.payments.persistence.dto;

import com.poku.graypants.domain.orderItem.persistence.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoPayClientReadyRequestDto {

    private String orderAddr;
    private String orderPhone;
    private List<Long> itemIdList;
    private List<Integer> itemQuantityList;
    private Long userId;
    private Integer totalAmount;
    private String itemName;
    private int quantity;
    private List<String> storeNameList;


    @Builder
    public KakaoPayClientReadyRequestDto(List<String> storeNameList, List<Long> itemIdList, List<Integer> itemQuantityList, String orderAddr, String orderPhone, Integer totalAmount, String itemName, int quantity) {
        this.itemIdList = itemIdList;
        this.itemQuantityList = itemQuantityList;
        this.orderAddr = orderAddr;
        this.orderPhone = orderPhone;
        this.totalAmount = totalAmount;
        this.itemName = itemName;
        this.quantity = quantity;
        this.storeNameList = storeNameList;
    }

    public void updateUserId(Long userId) {
        this.userId = userId;
    }
}
