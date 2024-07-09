package com.poku.graypants.domain.user.application;


import com.poku.graypants.domain.cart.persistence.Cart;
import com.poku.graypants.domain.cart.persistence.CartRepository;
import com.poku.graypants.domain.mail.application.MailService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;

import lombok.AllArgsConstructor;
import jakarta.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MailService mailService;
    private final String DEFAULT_ROLE = "ROLE_DEFAULT";
    private final CartRepository cartRepository;


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


    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GrayPantsException(ExceptionStatus.USER_NOT_FOUND));
    }

    public Cart getCartByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new GrayPantsException(ExceptionStatus.USER_NOT_FOUND);
        }
        Cart cart = user.get().getCart();
        if(cart == null)
            return createCart(user.get());
        return cart;
    }

    public Cart createCart(User user) {
        return cartRepository.save(Cart.builder()
                .user(user).build());
    }

    public User saveEmailUser(String email, String name, String password) {
        if (userRepository.findByEmail(email).isPresent())
            throw new GrayPantsException(ExceptionStatus.DUPLICATED_EMAIL);
        return userRepository.save(User.builder()
                .grade(DEFAULT_ROLE)
                .email(email)
                .username(name)
                .password(password)
                .build());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

}
