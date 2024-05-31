package com.rpEmpresa.web_app.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateProjectDto {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;


    @NotNull
    @NotBlank
    @NotEmpty
    private Long sectorId;

}