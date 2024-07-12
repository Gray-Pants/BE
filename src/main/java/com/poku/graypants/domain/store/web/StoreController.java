package com.poku.graypants.domain.store.web;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.global.util.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;


@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;


    @PreAuthorize("hasRole('ROLE_STORE')")
    @GetMapping("/myitems")
    public ResponseEntity<ApiResponseUtil.ApiResult<List<ItemResponseDto>>> getItemList(Authentication authentication) {
        List<ItemResponseDto> response = storeService.getItemByStoreId((Long) authentication.getPrincipal());
        return new ResponseEntity(success(response), HttpStatus.OK);
    }
}
