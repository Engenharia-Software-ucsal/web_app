package com.rpEmpresa.web_app.execeptions;

public class ResourceNotFoundExeception extends RuntimeException {

    public ResourceNotFoundExeception(String message) {
        super(message);
    }
}
