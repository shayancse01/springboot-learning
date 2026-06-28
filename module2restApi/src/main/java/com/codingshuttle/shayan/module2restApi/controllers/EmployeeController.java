package com.codingshuttle.shayan.module2restApi.controllers;

import com.codingshuttle.shayan.module2restApi.entities.EmployeeEntity;
import com.codingshuttle.shayan.module2restApi.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy) {
        return employeeService.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeService.save(inputEmployee);
    }
}
