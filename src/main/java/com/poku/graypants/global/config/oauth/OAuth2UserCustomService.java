package com.poku.graypants.global.config.oauth;

import com.poku.graypants.domain.auth.application.OAuthService;
import com.poku.graypants.domain.auth.persistence.OAuth;
import com.poku.graypants.domain.user.persistence.User;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfo;
import com.poku.graypants.global.config.oauth.info.OAuth2UserInfoFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


/**
 * OAuth2UserCustomService OAuth2User 를 커스터마이징하는 서비스
 */

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final OAuthService oAuthService;

    /**
     * OAuth2UserRequest 를 받아서 UserRepository 에 저장 혹은 닉네임 업데이트 후 OAuth2User 를 반환하는 메소드
     *
     * @param userRequest OAuth2UserRequest 객체
     * @return OAuth2User 객체
     */
    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2user = super.loadUser(userRequest);
        String oAuthType = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.createOAuth2MemberInfo(oAuthType, oauth2user.getAttributes());
        User user = saveOrGet(userInfo);
        return new PrincipalDetails(user, oauth2user.getAttributes());
    }


    /**
     * OAuth2UserInfo 를 받아서 UserRepository 에 저장 혹은 반환하는 메소드
     *
     * @param oAuth2UserInfo OAuth2UserInfo 객체
     * @return User 객체
     */
    private User saveOrGet(final OAuth2UserInfo oAuth2UserInfo) {
        OAuth oAuth = oAuthService.saveOrGetOAuthByOAuth2UserInfo(oAuth2UserInfo);

        return oAuth.getUser();
    }
}
