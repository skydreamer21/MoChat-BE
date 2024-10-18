package com.midasdev.mochat.config.security.Oauth;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthAccount {

    @Enumerated
    @Column(nullable = false)
    private OauthProvider oauthProvider;

    @Column(nullable = false)
    private String oauthSub;
}
