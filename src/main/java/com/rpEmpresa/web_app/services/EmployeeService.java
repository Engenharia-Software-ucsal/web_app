package com.rpEmpresa.web_app.services;


import com.rpEmpresa.web_app.entity.Dependent;
import com.rpEmpresa.web_app.enums.Role;
import com.rpEmpresa.web_app.http.dto.DependentDto;
import com.rpEmpresa.web_app.http.dto.EmployeeDto;
import com.rpEmpresa.web_app.database.PgConnection;
import com.rpEmpresa.web_app.entity.Employee;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

    public Boolean addDependentEmployee(DependentDto dto) {

        try(Connection con = this.db.getConnection()) {
            String sql = "INSERT INTO dependent (name,cpf,employee_id) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            var dependent = dto.toEntity();

            ps.setString(1, dependent.getName());
            ps.setString(2, dependent.getCpf());
            ps.setLong(3, dependent.getEmployeeId());

            ps.executeUpdate();

            ps.close();

            return true;

        }catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

    public List<Employee> findAllEmployeesWithDependents() {
        try(Connection con = this.db.getConnection()) {
            String query = """
                    SELECT e.cpf as employee_cpf,e.role as employee_role, e.id as employee_id, e.name as employee_name, d.id
                        as dependent_id, d.name as dependent_name, d.cpf as dependent_cpf, d.employee_id as dependent_for
                    FROM employee e
                        LEFT JOIN dependent d on e.id = d.employee_id ORDER BY  e.id;""";


            Statement stmt = con.createStatement();

            var results =  stmt.executeQuery(query);


            Map<Long, Employee> map = new HashMap<>();



            while (results.next()) {

                long employeeId =results.getLong("employee_id");
                long dependentId = results.getLong("dependent_id");


                Employee employee = map.get(employeeId);

                if(employee == null) {
                    employee = new Employee();
                    employee.setCpf(results.getString("employee_cpf"));
                    employee.setNome(results.getString("employee_name"));
                    employee.setRole(Role.fromString(results.getString("employee_role")));
                    employee.setId(employeeId);
                    map.put(employeeId, employee);

                }

                if(dependentId !=0){
                    var dependent = new Dependent();

                    dependent.setId(results.getLong("dependent_id"));
                    dependent.setName(results.getString("dependent_name"));
                    dependent.setCpf(results.getString("dependent_cpf"));
                    dependent.setEmployeeId(results.getLong("dependent_for"));

                    employee.getDependents().add(dependent);

                }




            }

            stmt.close();
            con.close();

            return new ArrayList<>(map.values());

        }catch (SQLException e) {

            e.printStackTrace();

            return null;
        }


    }

}
