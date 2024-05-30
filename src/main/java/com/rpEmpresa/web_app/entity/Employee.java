package com.rpEmpresa.web_app.entity;

import com.rpEmpresa.web_app.enums.Role;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;


@Setter
@Getter
public class Employee implements Serializable {
    String nome;
    String cpf;
    Long id;
    Role role;
    ArrayList<Dependent> dependents;


    public Employee(Long id, String nome, String cpf, Role role) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.role = role;
        this.dependents = new ArrayList<>();
    }

    public Employee() {
        this.dependents = new ArrayList<>();
    }



}
