package com.rpEmpresa.web_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class Dependent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private  String name;
    private String cpf;
    private Long employeeId;

    public Dependent() {

    }

    public Dependent(Long id, String name, String cpf, Long employeeId) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.employeeId = employeeId;
    }
}
