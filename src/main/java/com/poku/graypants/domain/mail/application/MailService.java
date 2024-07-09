package com.poku.graypants.domain.mail.application;

import com.poku.graypants.domain.mail.application.dto.VerificationEmailSendRequestDto;
import com.poku.graypants.domain.mail.application.dto.VerifyEmailRequestDto;
import com.poku.graypants.domain.mail.application.dto.VerifyEmailResponseDto;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import com.poku.graypants.global.util.RedisUtil;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = createMailMessage(to, subject, text);
        javaMailSender.send(message);
    }

    private SimpleMailMessage createMailMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }

    public void sendCodeToEmail(VerificationEmailSendRequestDto request) {
        String title = "Gray-Pants 이메일 인증 번호";
        String authCode = this.createCode();
        this.sendMail(request.getEmail(), title, authCode);
        redisUtil.setData("AuthCode " + request.getRole() + request.getEmail(), authCode, Duration.ofMinutes(10));
    }

    private String createCode() {
        int length = 6;
        Random random = null;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new GrayPantsException(ExceptionStatus.RANDOM_NUMBER_GENERATE_FAILED);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    public boolean verifiedCode(VerifyEmailRequestDto request) {
        String authCode = (String) redisUtil.getData("AuthCode "+ request.getRole() + request.getEmail());
        if(!request.getAuthCode().equals(authCode))
            return false;
        redisUtil.deleteData("AuthCode "+ request.getRole() + request.getEmail());
        return true;
    }

    public VerifyEmailResponseDto getVerifiedCode(VerifyEmailRequestDto request) {
        String verifiedCode = java.util.UUID.randomUUID().toString();
        redisUtil.setData("VerificationCode "+ request.getRole() + request.getEmail(), verifiedCode, Duration.ofMinutes(30));
        return VerifyEmailResponseDto.builder()
                .verifiedCode(verifiedCode)
                .build();
    }
}
