package com.rpEmpresa.web_app.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN(1),
    GENERAL(2);

    private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.name();

    }

    public static Role fromRoleId(int roleId) {
        for (Role role : Role.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        throw new IllegalArgumentException("No UserRole with roleId " + roleId + " found");
    }

}



