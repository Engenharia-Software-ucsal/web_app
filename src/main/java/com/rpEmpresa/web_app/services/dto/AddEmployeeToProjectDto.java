package com.rpEmpresa.web_app.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddEmployeeToProjectDto {

    @NotNull
    private  Long projectId;

    @NotNull
    private  Long employeeId;

}
