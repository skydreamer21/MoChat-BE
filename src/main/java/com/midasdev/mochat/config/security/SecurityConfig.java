package com.midasdev.mochat.config.security;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/v1/sample")
                        );
    }

    @Bean
    public SecurityFilterChain oidcLoginFilterChain(HttpSecurity httpSecurity) throws Exception{
        log.info("SecurityFilterChain -> OidcLoginFilterChain");
        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                                .baseUri("/security/oauth2/authorization"))
                        .userInfoEndpoint(config -> config.oidcUserService(oidcUserService()))
                        .successHandler(authenticationSuccessHandler)
                )
                .build();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();
    }

    @Bean
    ObservationRegistryCustomizer<ObservationRegistry> addTextHandler() {
        return registry -> registry.observationConfig().observationHandler(new ObservationTextPublisher());
    }

}
