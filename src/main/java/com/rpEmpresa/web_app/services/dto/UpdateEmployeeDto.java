package com.rpEmpresa.web_app.services.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmployeeDto {

    public  String name;

    public String role;

    @NotBlank
    public Long id;


}
