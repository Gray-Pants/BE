package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.mail.application.MailService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import jakarta.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final String DEFAULT_ROLE = "ROLE_DEFAULT";

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public User saveOrGetUserByOAuth2Info(OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElseGet(() -> saveUser(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName()));
    }
    public User saveUser(String email, String username) {
        return userRepository.save(User.builder()
                .grade(DEFAULT_ROLE)
                .email(email)
                .username(username)
                .build());
    }

    public void sendCodeToEmail(String email) {
        String title = "Gray-Pants 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendMail(email, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
//        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
//                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
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
