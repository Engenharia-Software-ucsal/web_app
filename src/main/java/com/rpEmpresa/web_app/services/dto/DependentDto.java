package com.rpEmpresa.web_app.services.dto;


import com.rpEmpresa.web_app.entity.Dependent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;


@Data
public class DependentDto {

    public String id;

    @NotNull
    @NotBlank
    public String name;

    @NotNull
  //  @CPF
    public  String cpf;

    @NotNull
    public Long employeeId;


    public Dependent toEntity() {
        return new Dependent(
                this.id == null ? null : Long.parseLong(id),
                this.name,
                this.cpf,
                this.employeeId
        );


    }
}
