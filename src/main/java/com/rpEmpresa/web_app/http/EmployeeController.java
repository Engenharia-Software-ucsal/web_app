package com.rpEmpresa.web_app.http;

import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.http.dto.DependentDto;
import com.rpEmpresa.web_app.http.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.rpEmpresa.web_app.services.EmployeeService;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private  final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<Boolean> createEmployee(@RequestBody @Validated EmployeeDto dto) {


        return ResponseEntity.ok(employeeService.createEmployee(dto));
    }


    @PostMapping("/dependent")
    public @ResponseBody ResponseEntity<Boolean> addDependent(@RequestBody @Validated DependentDto dto) {


        var results = this.employeeService.addDependentEmployee(dto);

        return results ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);

    }


    @GetMapping
    public @ResponseBody ResponseEntity<List<Employee>> findAllEmployeeWithDependents() {

        return ResponseEntity.ok().body(this.employeeService.findAllEmployeesWithDependents());

    }



}
