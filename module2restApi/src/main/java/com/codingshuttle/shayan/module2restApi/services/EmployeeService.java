package com.codingshuttle.shayan.module2restApi.services;

import com.codingshuttle.shayan.module2restApi.entities.EmployeeEntity;
import com.codingshuttle.shayan.module2restApi.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }


    public EmployeeEntity save(EmployeeEntity inputEmployee) {
        return employeeRepository.save(inputEmployee);
    }
}
