package com.midasdev.mochat.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

/**
 * Authentication 이후 어플리케이션 서버 토큰을 발행합니다.
 */
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperty jwtProperty;

    public String createAuthToken(OAuth2User oAuth2User) {
        if (oAuth2User instanceof OidcUser) {
            return creatAuthTokenForOidcUser((OidcUser) oAuth2User);
        }
        throw new IllegalArgumentException("Unknown oauth2User Type");
    }

    public String creatAuthTokenForOidcUser(OidcUser oidcUser) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("idToken", oidcUser.getIdToken().getTokenValue());
        attributes.put("type", TokenType.AUTH);
        Claims claims = new DefaultClaims(attributes);
        return generateToken(oidcUser.getSubject(), claims, jwtProperty.getAccessTokenExpiredSecond());
    }

    public String generateToken(String subject, Claims claims, Integer validationSecond) {
        Instant expiredTime = Instant.now().plus(validationSecond, ChronoUnit.SECONDS);
        return Jwts.builder()
                   .setSubject(subject)
                   .setClaims(claims)
                   .signWith(jwtProperty.getKey(), SignatureAlgorithm.HS512)
                   .setExpiration(Date.from(expiredTime))
                   .compact();
    }


}
