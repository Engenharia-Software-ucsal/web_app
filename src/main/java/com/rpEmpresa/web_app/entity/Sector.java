package com.rpEmpresa.web_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Sector implements Serializable {
    private  Long id;
    private String name;
    private String description;
}
