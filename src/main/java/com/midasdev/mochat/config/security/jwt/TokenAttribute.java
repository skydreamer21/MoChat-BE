package com.midasdev.mochat.config.security.jwt;

import lombok.Getter;

@Getter
public enum TokenAttribute {
    TYPE("type"), ID_TOKEN("idToken");

    private final String attribute;

    TokenAttribute(String attribute) {
        this.attribute = attribute;
    }
}
