package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.clients.impl;

import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.advice.ApiResponse;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.clients.EmployeeClient;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto.EmployeeDTO;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    //SLF4J log
    Logger log =  LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.trace("trying to retrieve all employee details in getAllEmployees()");

        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> { //This is the request and the responce Object

                        log.error((new String(res.getBody().readAllBytes())));

                        throw new ResourceNotFoundException("Cound not find the employees");
                    })
                    .body(new ParameterizedTypeReference<>() {
                        //Body method to convert the data to a particular type of object
                        //As we have to return it like List of employeeDTO and list is a parameterized type
                    });
            log.debug("successfully retrieved all employee details in getAllEmployees");
            log.trace("Retrieve employees list in getAllEmployees: {}", employeeDTOList);
            return employeeDTOList.getData();

        } catch (Exception e) {
            log.error("Exception occurred while retrieving all employee details in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to get employee by Id: {}", employeeId);
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> { //This is the request and the responce Object

                        log.error((new String(res.getBody().readAllBytes())));
                        throw new ResourceNotFoundException("Cound not find the employees");
                    })
                    .body(new ParameterizedTypeReference<>() {});
            return employeeResponse.getData();

        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById: {}", employeeId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create new employee with information: {}", employeeDTO);
        try {
            //if we want to return it as a Response entity we have to use ResponseEntity<> and toEntity instead of body
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> { //This is the request and the responce Object

                        log.debug("4xxClient error occurred while trying to create new employee");
                        log.error((new String(res.getBody().readAllBytes())));
                        throw new ResourceNotFoundException("Cound not create the employee");
                            })
                    .toEntity(new ParameterizedTypeReference<>() {});

            //we can also get the Headers, NOT RECOMMENDED
            System.out.println(employeeDTOApiResponse.getHeaders());

            log.trace("Successfully created a new employee: {}", employeeDTOApiResponse.getBody());

            return employeeDTOApiResponse.getBody().getData();

        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
