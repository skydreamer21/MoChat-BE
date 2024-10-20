package com.midasdev.mochat.auth.service;

import com.midasdev.mochat.auth.dto.TokenRequestUser;
import com.midasdev.mochat.auth.dto.request.AuthRequest;
import com.midasdev.mochat.config.security.jwt.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtValidator jwtValidator;

    public TokenRequestUser extractUserInfo(AuthRequest authRequest) {

        String idToken = jwtValidator.extractIdToken(authRequest.authToken());

        // 원하는 동작 -> idtoken 과 Provider를 input으로 넣으면 원하는 유저 정보가 담긴 TokenRequestUser 반환
        // IdTokenValidatorFactory를 내부 클래스로 만들어서 구현하자
        // 1. idTokenValidator에 extractUserInfo(provider) 호출
        // 2. 내부에서 createValidator 호출해서

    }

}
