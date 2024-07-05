package com.poku.graypants.domain.user.application;

import com.poku.graypants.domain.like.persistence.Like;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.exception.GrayPantsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final String DEFAULT_ROLE = "ROLE_DEFAULT";

    public User saveOrGetUserByOAuth2Info(OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.findByEmail(oAuth2UserInfo.getEmail())
                .orElseGet(() -> saveUser(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getName()));
    }

    public User saveUser(String email, String username) {
        return null;
    }

    public Like getLikeByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new GrayPantsException(ExceptionStatus.USER_NOT_FOUND);
        }
        Like like = user.get().getLike();
        if(cart == null)
            return createCart(user.get());
        return cart;
    }
    }
}
