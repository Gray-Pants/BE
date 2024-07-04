package com.poku.graypants.domain.item.web;

import com.poku.graypants.domain.item.application.ItemService;
import com.poku.graypants.domain.item.application.dto.ItemRequestDto;
import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable Long id) {
        ItemResponseDto responseDto = itemService.findById(id);
        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.FOUND);

    }

    @PostMapping("/item")
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto itemRequestDto) {
        ItemResponseDto responseDto = itemService.createItem(itemRequestDto);

        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@RequestBody ItemRequestDto itemRequestDto, @PathVariable Long id) {
        ItemResponseDto responseDto = itemService.updateItem(id, itemRequestDto);
        return new ResponseEntity<>(responseDto, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<List<ItemResponseDto>> getItemsByName(@PathVariable String itemName) {
        List<ItemResponseDto> searchItemList = itemService.findByNameAll(itemName);
        return new ResponseEntity<>(searchItemList, new HttpHeaders(), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id, HttpServletResponse response) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
