package com.poku.graypants.domain.item.web;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemCreateDto;
import com.poku.graypants.domain.item.application.dto.ItemRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.item.application.dto.ItemUpdateDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable Long id) {
        ItemResponseDto responseDto = itemService.findById(id);
        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.FOUND);

    }

    @PostMapping("/item")
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemCreateDto itemCreateDto) {
        ItemResponseDto responseDto = itemService.createItem(itemCreateDto);

        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@RequestBody ItemUpdateDto itemUpdateDto, @PathVariable Long id) {
        ItemResponseDto responseDto = itemService.updateItem(id, itemUpdateDto);
        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<List<ItemResponseDto>> getItemsByName(@PathVariable String itemName) {
        List<ItemResponseDto> searchItemList = itemService.findByNameAll(itemName);
        return new ResponseEntity<>(searchItemList, new HttpHeaders(), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
