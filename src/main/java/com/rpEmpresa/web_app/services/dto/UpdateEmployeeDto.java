package com.rpEmpresa.web_app.services.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEmployeeDto {

    public  String name;

    public Integer roleId;

    @NotNull
    public Long id;


}
