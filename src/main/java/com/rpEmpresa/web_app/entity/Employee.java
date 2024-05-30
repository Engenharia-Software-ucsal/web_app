package com.rpEmpresa.web_app.entity;

import com.rpEmpresa.web_app.enums.Role;


import java.io.Serializable;


public class Employee implements Serializable {
    String nome;
    String cpf;
    Long id;
    Role role;


    public Employee(Long id, String nome, String cpf, Role role) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.role = role;
    }

    public Employee() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
