package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.item.application.dto.ItemResponseDto;
import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.global.util.ApiResponseUtil;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poku.graypants.global.util.ApiResponseUtil.success;


@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 찜 목록 전체 조회
    @GetMapping("/{id}") // userId를 인자로 받는다
    public ResponseEntity<List<LikeResponseDto>> getLikes(@PathVariable Long id) {
        List<LikeResponseDto> responseDtoList = likeService.findByUserId(id);
        return ResponseEntity.ok(responseDtoList);
    }
}