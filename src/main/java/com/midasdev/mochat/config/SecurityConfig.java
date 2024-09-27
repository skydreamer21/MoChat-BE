package com.midasdev.mochat.config;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

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
//                        .userInfoEndpoint(config -> config .oidcUserService())
                )
                .build();
    }

}
