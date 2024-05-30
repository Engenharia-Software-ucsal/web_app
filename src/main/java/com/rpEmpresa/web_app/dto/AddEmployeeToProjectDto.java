package com.rpEmpresa.web_app.dto;

import lombok.Data;

@Data
public class AddEmployeeToProjectDto {
    private  Long projectId;
    private  Long employeeId;

}
