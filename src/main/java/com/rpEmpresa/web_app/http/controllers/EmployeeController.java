package com.rpEmpresa.web_app.http.controllers;

import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.dto.DependentDto;
import com.rpEmpresa.web_app.dto.EmployeeDto;
import com.rpEmpresa.web_app.dto.UpdateEmployeeDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rpEmpresa.web_app.services.EmployeeService;


import java.util.List;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Void> createEmployee(@RequestBody @Valid EmployeeDto dto) {


        try {
            employeeService.createEmployee(dto);

            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        }catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PostMapping("/dependent")
    public @ResponseBody ResponseEntity<String> addDependent(@RequestBody @Valid DependentDto dto) {

       try {
           this.employeeService.addDependentEmployee(dto);


           return ResponseEntity.status(HttpStatus.CREATED).build();

       }catch (Exception e) {

           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }


    @GetMapping
    public @ResponseBody ResponseEntity<List<Employee>> findAllEmployeeWithDependents() {

        try {
            return ResponseEntity.ok().body(this.employeeService.findAllEmployeesWithDependents());
        }catch (Exception e) {

            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {

        var results = this.employeeService.findById(id);

        return ResponseEntity.ok().body(results);
    }


    @PutMapping("")
    public @ResponseBody ResponseEntity<String> updateEmployee(@RequestBody @Valid UpdateEmployeeDto dto) {

        try {
            this.employeeService.updateEmployee(dto);

            return ResponseEntity.ok().body("Employee updated");

        }catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        try {

            this.employeeService.deleteEmployee(id);

            return ResponseEntity.ok().body("Employee deleted");
        }catch ( Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
