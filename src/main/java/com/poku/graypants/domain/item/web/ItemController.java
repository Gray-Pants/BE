package com.poku.graypants.domain.item.web;

import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemCreateRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemUpdateRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<ItemResponseDto>> getItem(@PathVariable Long id) {
        ItemResponseDto responseDto = itemService.findById(id);
        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping("/item")
    public ResponseEntity<ApiResult<ItemResponseDto>> createItem(@ModelAttribute ItemCreateRequestDto itemCreateRequestDto) {
        ItemResponseDto responseDto = itemService.createItem(itemCreateRequestDto);

        return new ResponseEntity<>(success(responseDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_STORE')")
    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@RequestBody ItemUpdateRequestDto itemUpdateRequestDto,
                                                      @PathVariable Long id) {
        ItemResponseDto responseDto = itemService.updateItem(id, itemUpdateRequestDto);
        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<List<ItemResponseDto>> getItemsByName(@PathVariable String itemName) {
        List<ItemResponseDto> searchItemList = itemService.findByNameAll(itemName);
        return new ResponseEntity<>(searchItemList, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
