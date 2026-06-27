package com.codingshuttle.shayan.module2restApi.controllers;

import com.codingshuttle.shayan.module2restApi.dto.EmployeeDTO;
import com.codingshuttle.shayan.module2restApi.entities.EmployeeEntity;
import com.codingshuttle.shayan.module2restApi.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage() {
//
//        return "secret message: jdnkjndokas";
//    }

    private final EmployeeRepository employeeRepository;

    //Doing Constructor injection of EmployeeEntity Class
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    //public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {}
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        //return new EmployeeDTO(id, "Shayan", "shayan@gmail.com", 21,LocalDate.of(2024, 10, 10), true);
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployee(@RequestParam Integer age, @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

//    @PostMapping
//    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
//        inputEmployee.setId(1000L);
//        return inputEmployee;
//    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }


    //The Object to JSON and JSON to Object Mapping is done by HttpMessageConverter
}
