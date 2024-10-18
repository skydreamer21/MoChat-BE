package com.midasdev.mochat.member.domain;

import com.midasdev.mochat.config.security.Oauth.OauthAccount;
import com.midasdev.mochat.global.audit.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthAccount oauthAccount;

    @Column(nullable = false)
    private String name;

    @Column
    private String profileImageUrl;

    @Column(nullable = false)
    private Boolean isDeleted;

}
