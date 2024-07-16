package com.poku.graypants.global.jwt;

import com.poku.graypants.domain.auth.persistence.EmailAuthenticateAble;
import com.poku.graypants.domain.user.persistence.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    /**
     * 토큰 발급
     *
     * @param user    User 객체
     * @param duration 만료 시간
     * @return String
     */
    public String generateToken(EmailAuthenticateAble entity, Duration duration) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + duration.toMillis()), entity);
    }

    /**
     * 토큰 생성
     *
     * @param member Member 객체
     * @return String
     */
    public String makeToken(Date expirationDate, EmailAuthenticateAble entity) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
//                .setSubject(user.getEmail())
                .claim("id", entity.getId())
                .claim("role", entity.getRole())
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * 토큰 유효성 검사
     *
     * @param token String
     * @return boolean
     */
    public boolean validToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 토큰으로부터 Authentication 객체를 가져옴
     *
     * @param token String
     * @return Authentication
     */
    public Authentication getAuthentication(final String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(claims.get("role", String.class)));
        return new UsernamePasswordAuthenticationToken(
                claims.get("id", Long.class),
                token,
                authorities
        );
    }

    /**
     * 토큰으로부터 Claims를 가져옴
     *
     * @param token String
     * @return Claims
     */
    private Claims getClaims(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build();
        return parser.parseClaimsJws(token).getBody();
    }

    /**
     * 서명 키 생성
     *
     * @return SecretKey
     */
    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }
}
