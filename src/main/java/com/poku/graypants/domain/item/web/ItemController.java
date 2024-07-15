package com.poku.graypants.domain.item.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemCreateRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemLikesResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemUpdateRequestDto;
import com.poku.graypants.domain.store.application.StoreService;
import com.poku.graypants.domain.store.persistence.Store;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final StoreService storeService;

    @GetMapping("/item/{id}")
    public ResponseEntity<ApiResult<ItemResponseDto>> getItem(@PathVariable Long id) {
        ItemResponseDto responseDto = itemService.findById(id);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/item")
    public ResponseEntity<ApiResult<ItemResponseDto>> createItem(@AuthenticationPrincipal Long storeId,  @ModelAttribute ItemCreateRequestDto itemCreateRequestDto) {

        Store store = storeService.getStoreById(storeId);
        log.info("Request Data: itemName={}, itemCategory={}, itemPrice={}, stock={}",
                itemCreateRequestDto.getItemName(),
                itemCreateRequestDto.getCategory(),
                itemCreateRequestDto.getItemPrice(),
                itemCreateRequestDto.getStock());
        ItemResponseDto responseDto = itemService.createItem(itemCreateRequestDto, store);

        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/item/{id}")
    public ResponseEntity<ApiResult<ItemResponseDto>> updateItem(@RequestBody ItemUpdateRequestDto itemUpdateRequestDto,
                                                                 @PathVariable Long id) {
        ItemResponseDto responseDto = itemService.updateItem(id, itemUpdateRequestDto);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<ApiResult<List<ItemResponseDto>>> getItemsByName(@PathVariable String itemName, @RequestParam(required = false, defaultValue = "modifiedAt") String sort) {
        List<ItemResponseDto> searchItemList = itemService.findAllByName(itemName, sort);
        return new ResponseEntity<>(success(searchItemList), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ApiResult<List<ItemResponseDto>>> findAll(@RequestParam(required = false, defaultValue = "modifiedAt") String sort) {
        List<ItemResponseDto> itemList = itemService.findAll();
        return new ResponseEntity<>(success(itemList), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<ApiResult<List<ItemResponseDto>>> findByCategory(@RequestParam String category, @RequestParam(required = false, defaultValue = "modifiedAt") String sort) {
        log.info(sort);
        List<ItemResponseDto> itemList = itemService.findByCategory(category, sort);
        return new ResponseEntity<>(success(itemList), new HttpHeaders(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_STORE')")
    @DeleteMapping("/item/{id}")
    public ResponseEntity<ApiResult<Void>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("{itemId}/likes/count")
    public ResponseEntity<ApiResult<ItemLikesResponseDto>> getLikeCounts(@PathVariable Long itemId) {
        ItemLikesResponseDto responseDto = itemService.getLikesCount(itemId);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);
    }
}
