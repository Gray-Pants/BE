package com.poku.graypants.domain.item.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemCreateRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemUpdateRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PreAuthorize("hasRole('ROLE_STORE')")
    @GetMapping("/item/{id}")
    public ResponseEntity<ApiResult<ItemResponseDto>> getItem(@PathVariable Long id) {
        ItemResponseDto responseDto = itemService.findById(id);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/item")
    public ResponseEntity<ApiResult<ItemResponseDto>> createItem(@ModelAttribute ItemCreateRequestDto itemCreateRequestDto) {

        ItemResponseDto responseDto = itemService.createItem(itemCreateRequestDto);

        log.info(itemCreateRequestDto.toString());

        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/item/{id}")
    public ResponseEntity<ApiResult<ItemResponseDto>> updateItem(@RequestBody ItemUpdateRequestDto itemUpdateRequestDto,
                                                      @PathVariable Long id) {
        ItemResponseDto responseDto = itemService.updateItem(id, itemUpdateRequestDto);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @GetMapping("/{itemName}")
    public ResponseEntity<ApiResult<List<ItemResponseDto>>> getItemsByName(@PathVariable String itemName) {
        log.info("Getting items by name: {}", itemName);
        List<ItemResponseDto> searchItemList = itemService.findByNameAll(itemName);
        return new ResponseEntity<>(success(searchItemList), new HttpHeaders(), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_STORE')")
    @DeleteMapping("/item/{id}")
    public ResponseEntity<ApiResult<Void>> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
    }


}
