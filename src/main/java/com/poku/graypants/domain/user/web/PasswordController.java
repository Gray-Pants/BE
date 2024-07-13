package com.poku.graypants.domain.user.web;

import com.poku.graypants.domain.user.application.PasswordService;
import com.poku.graypants.domain.user.application.dto.PasswordEditRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/change")
    public ResponseEntity<?> changePassword(@RequestBody PasswordEditRequestDto passwordDto) {
        try {
            boolean success = passwordService.changePassword(passwordDto);
            if (success) {
                return ResponseEntity.ok(ApiResponseUtil.success("비밀번호가 성공적으로 변경되었습니다."));
            } else {
                return ResponseEntity.badRequest().body(ApiResponseUtil.error("현재 비밀번호가 일치하지 않습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponseUtil.error("비밀번호 변경 중 오류가 발생했습니다."));
        }
    }
}