package com.rpEmpresa.web_app.http.controllers;

import com.rpEmpresa.web_app.services.dto.AddEmployeeToProjectDto;
import com.rpEmpresa.web_app.services.dto.CreateProjectDto;
import com.rpEmpresa.web_app.entity.Project;
import com.rpEmpresa.web_app.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private  final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping
    public @ResponseBody ResponseEntity<String> createSector(@RequestBody @Validated CreateProjectDto dto) {
        try {
            this.projectService.createProject(dto);

            return ResponseEntity.ok().body("project created");

        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-employee")
    public @ResponseBody ResponseEntity<String> addEmployee(@RequestBody @Validated AddEmployeeToProjectDto dto) {

        try {
            this.projectService.addEmployeeInProject(dto.getProjectId(), dto.getEmployeeId());

            return ResponseEntity.ok().body("employee added");
        }catch ( Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Project>> fetchAllProjectsWithEmployees() {

        try {
            return ResponseEntity.ok().body(this.projectService.findAllProjectsWithEmployees());

        }catch ( Exception e) {

            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }

}
