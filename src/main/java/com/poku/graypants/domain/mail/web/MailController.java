package com.poku.graypants.domain.mail.web;

import static com.poku.graypants.global.util.ApiResponseUtil.error;
import static com.poku.graypants.global.util.ApiResponseUtil.success;

import com.poku.graypants.domain.mail.application.MailService;
import com.poku.graypants.global.util.ApiResponseUtil.ApiError;
import com.poku.graypants.global.util.ApiResponseUtil.ApiResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mails")
@AllArgsConstructor
public class MailController {

    MailService mailService;

    @GetMapping("/new-mail/{to}")
    public String sendMail(@PathVariable String to) {
        mailService.sendMail(to, "test", "test");
        return "send mail to " + to;
    }


    @PostMapping("/verification-requests")
    public ResponseEntity<ApiResult<String>> sendMessage(@RequestParam("email") String email) {
        mailService.sendCodeToEmail(email);

        return new ResponseEntity<>(success("메세지가 전송되었습니다."), HttpStatus.OK);
    }

    @GetMapping("/verifications")
    public ResponseEntity<ApiResult<?>> verificationEmail(@RequestParam("email") String email,
                                                                 @RequestParam("code") String authCode) {
        if(mailService.verifiedCode(email, authCode))
            return new ResponseEntity<>(success("인증 성공"), HttpStatus.OK);
        return new ResponseEntity<>(error("인증 실패"), HttpStatus.FORBIDDEN);
    }
}
