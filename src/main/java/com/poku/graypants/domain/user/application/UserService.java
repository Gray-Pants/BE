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


}
