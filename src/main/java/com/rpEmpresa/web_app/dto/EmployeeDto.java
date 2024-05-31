package com.rpEmpresa.web_app.dto;


import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class EmployeeDto {

    public String id;

    @NotBlank
    public String name;

    @CPF
    public  String cpf;


    @NotBlank
    public  String role;


    public Employee toEmployee() {
        return new Employee(
                this.id == null ? null : Long.parseLong(id),
                this.name,
                this.cpf,
                Role.fromString(role)
        );

    }
}
