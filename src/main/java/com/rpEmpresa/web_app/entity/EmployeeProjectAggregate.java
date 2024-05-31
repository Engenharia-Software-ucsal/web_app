package com.rpEmpresa.web_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EmployeeProjectAggregate implements Serializable {

    private  Long employeeId;
    private  Long projectId;

    private Date dateInit;

}
