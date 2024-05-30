package com.rpEmpresa.web_app.services;


import com.rpEmpresa.web_app.enums.Role;
import com.rpEmpresa.web_app.http.dto.EmployeeDto;
import com.rpEmpresa.web_app.database.PgConnection;
import com.rpEmpresa.web_app.entity.Employee;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class EmployeeService {

    private  final PgConnection db;

    public EmployeeService(PgConnection connection) {
        this.db = connection;
    }

    public boolean createEmployee(EmployeeDto dto) {
        try(Connection con = this.db.getConnection()) {
            String sql = "INSERT INTO employee (name,cpf,role) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            Employee ep = new Employee(null, dto.name, dto.cpf, Role.fromString(dto.role));

            ps.setString(1, ep.getNome());
            ps.setString(2, ep.getCpf());
            ps.setString(3, ep.getRole().getString());

            ps.executeUpdate();

            ps.close();

            return true;

        }catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

}
