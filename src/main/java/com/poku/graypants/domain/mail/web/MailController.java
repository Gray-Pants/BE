package com.poku.graypants.domain.mail.web;

import static com.poku.graypants.global.util.ApiResponseUtil.error;
import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.mail.application.MailService;
import com.poku.graypants.domain.mail.application.dto.VerificationEmailSendRequestDto;
import com.poku.graypants.domain.mail.application.dto.VerifyEmailRequestDto;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mails")
@AllArgsConstructor
public class MailController {

    MailService mailService;

    @GetMapping("/new-mail/{to}")
    public String sendMail(@PathVariable String to) {
        mailService.sendMail(to, "test", "test");
        return "send mail to " + to;
    }

    @PostMapping("/verification-requests")
    public ResponseEntity<ApiResult<String>> sendMessage(@RequestBody VerificationEmailSendRequestDto request) {
        mailService.sendCodeToEmail(request);

        return new ResponseEntity<>(success("메세지가 전송되었습니다."), HttpStatus.OK);
    }

    @PostMapping("/verifications")
    public ResponseEntity<ApiResult<?>> verificationEmail(@RequestBody VerifyEmailRequestDto request) {
        if(mailService.verifiedCode(request))
            return new ResponseEntity<>(success(mailService.getVerifiedCode(request)), HttpStatus.OK);
        return new ResponseEntity<>(error("인증 실패"), HttpStatus.FORBIDDEN);
    }
}
