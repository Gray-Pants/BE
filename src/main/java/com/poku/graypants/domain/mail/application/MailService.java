package com.poku.graypants.domain.mail.application;

import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

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

    public void sendCodeToEmail(String email) {
        String title = "Gray-Pants 이메일 인증 번호";
        String authCode = this.createCode();
        this.sendMail(email, title, authCode);
        valueOperations.set("AuthCode " + email, authCode, Duration.ofMinutes(10));
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

    public boolean verifiedCode(String email, String code) {
        String authCode = valueOperations.get("AuthCode " + email);
        if(!code.equals(authCode))
            return false;
        valueOperations.getAndDelete("AuthCode " + email);
        return true;
    }
}
