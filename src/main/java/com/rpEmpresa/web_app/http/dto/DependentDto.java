package com.rpEmpresa.web_app.http.dto;


import com.rpEmpresa.web_app.entity.Dependent;
import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.enums.Role;
import lombok.Data;

@Data
public class DependentDto {

    public String id;

    public String name;

    public  String cpf;

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
