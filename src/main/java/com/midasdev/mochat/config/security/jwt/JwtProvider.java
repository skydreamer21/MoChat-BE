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
import org.springframework.stereotype.Component;

/**
 * Authentication 이후 어플리케이션 서버 토큰을 발행합니다.
 */
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperty jwtProperty;

    public String createAuthToken(OidcUser oidcUser) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("idToken", oidcUser.getIdToken().getTokenValue());
        Claims claims = new DefaultClaims(attributes);
        return generateToken(oidcUser.getSubject(), claims, jwtProperty.getAccessTokenExpiredSecond());
    }

    public String generateToken(String subject, Claims claims, Integer validationSecond) {
        Instant expiredTime = Instant.now().plus(validationSecond, ChronoUnit.SECONDS);
        return Jwts.builder()
                   .setSubject(subject)
                   .setClaims(claims)
                   .signWith(jwtProperty.getKey(), SignatureAlgorithm.ES512)
                   .setExpiration(Date.from(expiredTime))
                   .compact();
    }



}
