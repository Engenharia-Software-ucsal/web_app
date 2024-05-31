package com.rpEmpresa.web_app.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSectorDto {
    @NotEmpty
    @NotNull
    public String name;


    @NotEmpty
    @NotNull
    public String description;

}
