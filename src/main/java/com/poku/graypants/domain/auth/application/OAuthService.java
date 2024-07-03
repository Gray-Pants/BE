package com.poku.graypants.domain.auth.application;

import com.poku.graypants.domain.auth.persistence.OAuth;
import com.poku.graypants.domain.auth.persistence.OAuthRepository;
import com.poku.graypants.domain.user.application.UserService;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.domain.user.persistence.UserRepository;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OAuthService {
    private final OAuthRepository oAuthRepository;
    private final UserService userService;


    public OAuth saveOrGetOAuthByOAuth2UserInfo(final OAuth2UserInfo oAuth2UserInfo) {
        String oauth2Id = getOAuthIdByOAuth2UserInfo(oAuth2UserInfo);

        Optional<OAuth> oauth = oAuthRepository.findByoAuthId(oauth2Id);
        return oauth.orElseGet(() -> saveOAuth(oAuth2UserInfo, oauth2Id));
    }

    public OAuth saveOAuth(final OAuth2UserInfo oAuth2UserInfo, final String oauth2Id) {
        OAuth newOauth = OAuth.builder()
                .oAuthId(oauth2Id)
                .provider(oAuth2UserInfo.getProvider())
                .providerId(oAuth2UserInfo.getProviderId())
                .user(userService.saveOrGetUserByOAuth2Info(oAuth2UserInfo))
                .build();
        return oAuthRepository.save(newOauth);
    }

    public String getOAuthIdByOAuth2UserInfo(final OAuth2UserInfo oAuth2UserInfo) {
        return oAuth2UserInfo.getProvider() + "_"
                + oAuth2UserInfo.getProviderId();
    }
}
