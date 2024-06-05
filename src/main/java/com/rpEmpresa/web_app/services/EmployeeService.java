package com.rpEmpresa.web_app.services;


import com.rpEmpresa.web_app.entity.Dependent;
import com.rpEmpresa.web_app.enums.Role;
import com.rpEmpresa.web_app.services.dto.DependentDto;
import com.rpEmpresa.web_app.services.dto.EmployeeDto;
import com.rpEmpresa.web_app.database.PgConnection;
import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.services.dto.UpdateEmployeeDto;
import com.rpEmpresa.web_app.execeptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class EmployeeService {

    private  final PgConnection db;

    public EmployeeService(PgConnection connection) {
        this.db = connection;
    }

    public void createEmployee(EmployeeDto dto) throws  Exception {

            Connection con = this.db.getConnection();

            String sql = "INSERT INTO employee (name,cpf,role) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            Employee ep = new Employee(null, dto.name, dto.cpf, Role.fromString(dto.role));

            ps.setString(1, ep.getNome());
            ps.setString(2, ep.getCpf());
            ps.setString(3, ep.getRole().getString());

            ps.executeUpdate();

            ps.close();
            con.close();

    }

    public void addDependentEmployee(DependentDto dto) throws  Exception{

            Connection con = this.db.getConnection();

            var existsEmployee = this.findById(dto.employeeId);

            if(existsEmployee == null) {
                throw  new ResourceNotFoundException("Employee Not found");
            }

           System.out.println(existsEmployee.getId());

            String sql = "INSERT INTO dependent (name,cpf,employee_id) VALUES (?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            var dependent = dto.toEntity();

            ps.setString(1, dependent.getName());
            ps.setString(2, dependent.getCpf());
            ps.setLong(3, dependent.getEmployeeId());

            ps.executeUpdate();

            ps.close();

    }

    public List<Employee> findAllEmployeesWithDependents(int page, int pageSize) throws Exception {


            Connection con = this.db.getConnection();

            String query = """
                    SELECT e.cpf as employee_cpf,e.role as employee_role, e.id as employee_id, e.name as employee_name, d.id
                        as dependent_id, d.name as dependent_name, d.cpf as dependent_cpf, d.employee_id as dependent_for
                    FROM employee e
                        LEFT JOIN dependent d on e.id = d.employee_id ORDER BY  e.id LIMIT ? OFFSET ?""";



            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1 , pageSize);
            stmt.setInt(2 , pageSize * page);

            var results =  stmt.executeQuery();


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



    }


    public Employee findById(Long id) {
        try (Connection con = this.db.getConnection()) {

            String sql = "SELECT * from employee WHERE employee.id = ?";

            PreparedStatement smt = con.prepareStatement(sql);

            smt.setLong(1, id);

            var results = smt.executeQuery();


            Employee employee = new Employee();

            while (results.next()) {
                employee.setId(results.getLong("id"));
                employee.setCpf(results.getString("cpf"));
                employee.setRole(Role.fromString(results.getString("role")));
                employee.setNome(results.getString("name"));
            }

            smt.close();
            con.close();

            return employee.getId() == null ? null : employee;

        } catch (SQLException e) {

            return null;
        }

    }

    public void  updateEmployee(UpdateEmployeeDto dto) throws  Exception{

        var  existsEmployee = this.findById(dto.getId());


        if(existsEmployee == null) {
            throw  new Exception("Not found");
        }

        Connection conn = this.db.getConnection();

        PreparedStatement smt = conn.prepareStatement("UPDATE employee SET name = ?, role = ? WHERE id = ?");


        if(dto.getName() != null) {
            existsEmployee.setNome(dto.getName());
        }

        if(dto.getRole() != null) {
            existsEmployee.setRole(Role.fromString(dto.getRole()));
        }

        smt.setString(1, existsEmployee.getNome());
        smt.setString(2,existsEmployee.getRole().getString());
        smt.setLong(3, existsEmployee.getId());

         smt.executeUpdate();

         smt.close();
         conn.close();
    }


    public void deleteEmployee(Long id) throws  Exception{

        var  existsEmployee = this.findById(id);


        if(existsEmployee == null) {
            throw  new Exception("Not found");
        }

        Connection conn = this.db.getConnection();

        PreparedStatement smt = conn.prepareStatement("DELETE FROM employee WHERE employee.id = ?");


        smt.setLong(1, id);

        smt.executeUpdate();

        smt.close();
        conn.close();
    }


}
