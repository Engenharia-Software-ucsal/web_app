package com.rpEmpresa.web_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddEmployeeToProjectDto {

    @NotNull
    @NotEmpty
    private  Long projectId;

    @NotNull
    @NotEmpty
    private  Long employeeId;

}
