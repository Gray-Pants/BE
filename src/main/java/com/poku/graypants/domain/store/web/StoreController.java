package com.poku.graypants.domain.store.web;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.store.application.MyOrderItemsResponseDto;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.global.util.ApiResponseUtil;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;


@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @PreAuthorize("hasRole('ROLE_STORE')")
    @GetMapping("/myitems")
    public ResponseEntity<ApiResult<List<ItemResponseDto>>> getItemList(Authentication authentication) {
        List<ItemResponseDto> response = storeService.getItemByStoreId((Long) authentication.getPrincipal());
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @GetMapping("/my-order-items")
    public ResponseEntity<ApiResult<MyOrderItemsResponseDto>> getMyOrderItems(@AuthenticationPrincipal Long storeId) {
        MyOrderItemsResponseDto response = storeService.getMyOrderItems(storeId);
        return new ResponseEntity<>(success(response), HttpStatus.OK);
    }
}
