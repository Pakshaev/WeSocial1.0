package com.example.wesocial1_0.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_DELETE("ADMIN_DELETE"),
    USER_READ("USER_READ"),
    USER_UPDATE("USER_UPDATE"),
    USER_CREATE("USER_CREATE"),
    USER_DELETE("USER_DELETE");

    @Getter
    private final String permission;
}
