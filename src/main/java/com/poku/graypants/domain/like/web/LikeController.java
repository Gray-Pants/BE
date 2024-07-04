package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.item.persistence.Item;
import com.poku.graypants.domain.like.application.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    @Autowired
    private LikeService likeService;

    //Like 페이지에 접속했을 때, 전체 조회
    @GetMapping("/likes")
    public ResponseEntity<List<Item>> getLikes(@RequestParam String email) { //(Authentication authentication){
        List<Item> likes = LikeService.getLikesByEmail(email);
        return ResponseEntity.ok(likes);
    }
}