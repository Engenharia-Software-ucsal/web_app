package com.rpEmpresa.web_app.http.dto;


import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.enums.Role;
import lombok.Data;

@Data
public class EmployeeDto {

    public String id;

    public String name;

    public  String cpf;
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
