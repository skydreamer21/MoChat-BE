package com.midasdev.mochat.auth.dto.request;

import com.midasdev.mochat.config.security.Oauth.OauthProvider;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank OauthProvider oauthProvider, @NotBlank String authToken) {

}
