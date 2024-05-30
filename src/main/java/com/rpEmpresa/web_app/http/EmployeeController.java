package com.rpEmpresa.web_app.http;

import com.rpEmpresa.web_app.http.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.rpEmpresa.web_app.services.EmployeeService;

import java.util.HashMap;


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

    @GetMapping("/status")
    public @ResponseBody ResponseEntity getEmployeeStatus() {

        var map = new HashMap<String, Object>();

        map.put("status", true);

        return ResponseEntity.status(200).body(map);
    }
}
