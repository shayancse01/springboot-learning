package com.codingshuttle.shayan.module2restApi.services;

import com.codingshuttle.shayan.module2restApi.dto.EmployeeDTO;
import com.codingshuttle.shayan.module2restApi.entities.EmployeeEntity;
import com.codingshuttle.shayan.module2restApi.exceptions.ResourceNotFoundException;
import com.codingshuttle.shayan.module2restApi.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {

//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        //return mapper.map(employeeEntities, EmployeeDTO.class);
        return employeeEntities
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        // to check if user is admin or if you want to log something
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    //for put mapping
    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
         isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public void isExistsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        //Throwing exception if the employeeId in not present
        if (!exists) throw new ResourceNotFoundException("Employee not found with this id: " + employeeId);
    }

    //for delete mapping
    public boolean deleteEmployeeById(Long employeeId) {
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    //for Patch mapping
    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        //First find the Employee from the database table with the help of employeeId
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        //Here we are using Reflection in JAVA to get the field
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field); //finding the required filed
            fieldToBeUpdated.setAccessible(true);//as field(Int, Age...etc) inside EmployeeEntity is private
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value); //updating the filed
        });
        //Now save the employeeEntity and convert it into EmployeeDTO
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
