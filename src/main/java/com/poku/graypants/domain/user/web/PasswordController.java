package com.poku.graypants.domain.user.web;

import com.poku.graypants.domain.user.application.PasswordService;
import com.poku.graypants.domain.user.application.dto.PasswordEditRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage/edit")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordEditRequestDto passwordDto, Authentication authentication) {
            boolean success = passwordService.changePassword(passwordDto, (Long) authentication.getPrincipal());
            if (success) {
                return ResponseEntity.ok(ApiResponseUtil.success("비밀번호가 성공적으로 변경되었습니다."));
            } else {
                return ResponseEntity.badRequest().body(ApiResponseUtil.error("현재 비밀번호가 일치하지 않습니다."));
            }

    }
}
