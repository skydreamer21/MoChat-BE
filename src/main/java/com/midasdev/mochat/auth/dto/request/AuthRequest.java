package com.midasdev.mochat.auth.dto.request;

import com.midasdev.mochat.config.security.Oauth.OauthProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequest(@NotNull OauthProvider oauthProvider, @NotBlank String authToken) {

}
