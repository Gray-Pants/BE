package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    //Like 페이지에 접속했을 때, 찜 목록 전체 조회
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getLikeItems(@RequestParam String email) { //(Authentication authentication){
        List<Item> likeItems = likeService.getLikeItems(email);
        return ResponseEntity.ok(likeItems);
    }
}