package com.example.wesocial1_0.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN_READ"),
    ADMIN_UPDATE("ADMIN_UPDATE"),
    ADMIN_CREATE("ADMIN_CREATE"),
    ADMIN_DELETE("ADMIN_DELETE"),
    ROLE1_READ("ROLE1_READ"),
    ROLE1_UPDATE("ROLE1_UPDATE"),
    ROLE1_CREATE("ROLE1_CREATE"),
    ROLE1_DELETE("ROLE1_DELETE");

    @Getter
    private final String permission;
}
