package com.rpEmpresa.web_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Project implements Serializable {

    private  Long id;
    private String name;
    private long sector_id;
    private List<Employee> employees;

    public Project() {
        this.employees = new ArrayList<>();
    }

}
