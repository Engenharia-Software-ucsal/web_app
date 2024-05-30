package com.rpEmpresa.web_app.http;

import com.rpEmpresa.web_app.entity.Employee;
import com.rpEmpresa.web_app.http.dto.DependentDto;
import com.rpEmpresa.web_app.http.dto.EmployeeDto;
import com.rpEmpresa.web_app.http.dto.UpdateEmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public @ResponseBody ResponseEntity<Boolean> createEmployee(@RequestBody @Validated EmployeeDto dto) {


        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(dto));
    }


    @PostMapping("/dependent")
    public @ResponseBody ResponseEntity addDependent(@RequestBody @Validated DependentDto dto) {

       try {
           var results = this.employeeService.addDependentEmployee(dto);

           return results ? ResponseEntity.status(HttpStatus.CREATED).body(null) : ResponseEntity.badRequest().body(false);

       }catch (Exception e) {

           return ResponseEntity.badRequest().body(e.getMessage());
       }

    }


    @GetMapping
    public @ResponseBody ResponseEntity<List<Employee>> findAllEmployeeWithDependents() {

        return ResponseEntity.ok().body(this.employeeService.findAllEmployeesWithDependents());

    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long id) {

        var results = this.employeeService.findById(id);

        return ResponseEntity.ok().body(results);
    }


    @PutMapping("")
    public @ResponseBody ResponseEntity<String> updateEmployee(@RequestBody @Validated UpdateEmployeeDto dto) {

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
