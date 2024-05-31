package com.rpEmpresa.web_app.services;


import com.rpEmpresa.web_app.database.PgConnection;
import com.rpEmpresa.web_app.services.dto.CreateProjectDto;
import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.entity.Project;

import com.rpEmpresa.web_app.execeptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectService {

    private  final PgConnection db;
    private final EmployeeService employeeService;

    public ProjectService(PgConnection connection, EmployeeService employeeService) {
        this.db = connection;
        this.employeeService = employeeService;

    }


    public void createProject(CreateProjectDto dto)  throws  Exception{

        var connection = this.db.getConnection();

        PreparedStatement smt = connection.prepareStatement(
                "INSERT INTO project  (name, sector_id) VALUES (?, ?)"
        );

        Project project = new Project();

        project.setName(dto.getName());
        project.setSector_id(dto.getSectorId());

        smt.setString(1, project.getName());
        smt.setLong(2, project.getSector_id());

        smt.executeUpdate();

        smt.close();
        connection.close();

    }

    public Project findById(Long id)  throws  Exception{
        String query = "SELECT * FROM project WHERE id = ?";

         var connection = this.db.getConnection();

         PreparedStatement smt = connection.prepareStatement(query);

         smt.setLong(1, id);

         ResultSet rs = smt.executeQuery();

        Project project = new Project();

         while (rs.next()) {

             project.setId(rs.getLong("id"));
             project.setName(rs.getString("name"));
             project.setSector_id(rs.getLong("sector_id"));
         }

        return project.getId() == null ? null : project;
    }


    public void  addEmployeeInProject(long project_id, long employee_id)  throws  Exception{

        Project existingProject = this.findById(project_id);

        if(existingProject == null){
            throw new ResourceNotFoundException("Project with id " + project_id + " not found");
        }

        Employee existsEmployee = employeeService.findById(employee_id);

        if(existsEmployee == null) {
            throw new ResourceNotFoundException("Employee with id " + employee_id + " not found");
        }

        String sql = "INSERT INTO employee_project (employee_id, project_id, date_init) VALUES (?, ?, ?)";

        Connection connection = this.db.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setLong(1, employee_id);
        statement.setLong(2, project_id);
        statement.setDate(3, Date.valueOf(LocalDate.now()));

        statement.executeUpdate();
        statement.close();
        connection.close();

    }

    public ArrayList<Project> findAllProjectsWithEmployees() throws Exception {

        String query = """
                SELECT pr.sector_id AS project_sector, pr.name as project_name, pr.id AS project_id, emp.name  AS emp_name,
                emp.id AS emp_id, emProject.date_init as data_init
                FROM project pr
                    INNER JOIN employee
                        emp INNER JOIN employee_project emProject
                    ON emp.id = emProject.employee_id
                            ON pr.id = emProject.project_id""";


        var connection = this.db.getConnection();

        PreparedStatement statement = connection.prepareStatement(query);

        var resulset = statement.executeQuery();

        Map<Long, Project> map = new HashMap<>();

        while (resulset.next()) {
            Long projectId = resulset.getLong("project_id");
            Long emp_id = resulset.getLong("emp_id");


            Project project = map.get(projectId);

            if(project == null) {
                project = new Project();
                project.setName(resulset.getString("project_name"));
                project.setId(projectId);
                project.setSector_id(resulset.getLong("project_sector"));
                map.put(projectId, project);

            }


            if(emp_id != -1) {
                Employee em = new Employee();
                em.setId(emp_id);
                em.setNome(resulset.getString("emp_name"));

                project.getEmployees().add(em);
            }


        }

        return new ArrayList<>(map.values());

    }




}
