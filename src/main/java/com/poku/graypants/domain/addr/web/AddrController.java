package com.poku.graypants.domain.addr.web;

import com.poku.graypants.domain.addr.application.AddrService;
import com.poku.graypants.domain.addr.application.Dto.AddrRequestDto;
import com.poku.graypants.domain.addr.application.Dto.AddrResponseDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.poku.graypants.global.util.ApiResponseUtil.success;

@RestController
@RequestMapping("/api/addr")
@RequiredArgsConstructor
public class AddrController {

    private final AddrService addrService;

    // 주소 생성
    @PostMapping
    public ResponseEntity<ApiResult<AddrResponseDto>> createAddr(@RequestBody AddrRequestDto request) {
        AddrResponseDto createdAddr = addrService.createAddr(request);
        return ResponseEntity.ok(success(createdAddr));
    }


    // 주소 조회
    @GetMapping("/{userAddrId}")
    public ResponseEntity<ApiResult<AddrResponseDto>> getAddrByUserAddrId(@PathVariable Long userAddrId) {
        AddrResponseDto addr = addrService.getUserById(userAddrId);
        return ResponseEntity.ok(success(addr));
    }

    // 주소 삭제
    @DeleteMapping("/{userAddrId}")
    public ResponseEntity<?> deleteAddr(@PathVariable Long userAddrId) {
        addrService.deleteUserAddr(userAddrId);
        return ResponseEntity.noContent().build();
    }

    // 주소 수정
    @PutMapping("/{userAddrId}")
    public ResponseEntity<ApiResult<AddrResponseDto>> updateUserAddr(
            @PathVariable Long userAddrId,
            @RequestBody ApiResult<AddrRequestDto> request) {
        AddrRequestDto addrRequestDto = request.getResponse();
        AddrResponseDto updatedAddr = addrService.updateAddr(userAddrId, addrRequestDto);
        return ResponseEntity.ok(success(updatedAddr));
    }
}