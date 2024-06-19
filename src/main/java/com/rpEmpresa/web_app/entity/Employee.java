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
    int roleId;
    ArrayList<Dependent> dependents;


  public Role getRole() {
        return Role.fromRoleId(roleId);
    }


    public Employee(Long id, String nome, String cpf, int roleId) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.roleId = roleId;
        this.dependents = new ArrayList<>();
    }

    public Employee() {
        this.dependents = new ArrayList<>();

    }



}
