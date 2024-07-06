package com.poku.graypants.domain.like.web;

import com.poku.graypants.domain.like.application.LikeService;
import com.poku.graypants.domain.like.application.dto.LikeResponseDto;
import com.poku.graypants.domain.user.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 찜 목록 전체 조회
    @GetMapping("/user/{userId}") // HTTP GET 요청을 처리하고, 경로 변수로 userId를 받는다
    public ResponseEntity<List<LikeResponseDto>> getAllLikesByUser (@PathVariable User userId) { // @PathVariable 어노테이션을 사용하여 URL 경로에서 userId를 변수로 받는다
        List<LikeResponseDto> responseDtoList = likeService.getAllLikesByUser(userId); // likeService의 getAllLikesByUser 메서드를 호출하여 해당 userId의 좋아요 목록을 가져온다, 가져온 목록을 LikeResponseDto 객체의 리스트로 변환한다
        return ResponseEntity.ok(responseDtoList); // 변환된 responseDtoList를 HTTP 응답 본문에 포함하여 200 OK 상태 코드와 함께 반환한다
    }

}