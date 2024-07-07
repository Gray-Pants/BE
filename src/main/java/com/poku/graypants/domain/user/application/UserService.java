package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.cart.persistence.Cart;
import com.poku.graypants.domain.cart.persistence.CartRepository;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.ExceptionStatus;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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

}
