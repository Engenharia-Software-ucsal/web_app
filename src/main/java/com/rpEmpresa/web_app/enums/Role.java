package com.rpEmpresa.web_app.enums;

public enum Role {
    PROVIDER,
    MANAGER;

    public String getString() {
        return this.toString();
    }


    public static Role fromString(String role) {

        return Role.valueOf(role);
    }

}
