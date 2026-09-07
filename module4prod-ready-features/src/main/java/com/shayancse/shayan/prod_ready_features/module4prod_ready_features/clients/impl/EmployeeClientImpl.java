package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.clients.impl;

import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.advice.ApiResponse;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.clients.EmployeeClient;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto.EmployeeDTO;
import com.shayancse.shayan.prod_ready_features.module4prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                        //Body method to convert the data to a particular type of object
                        //As we have to return it like List of employeeDTO and list is a parameterized type
                    });
            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return employeeResponse.getData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            //if we want to return it as a Response entity we have to use ResponseEntity<> and toEntity instead of body
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> { //This is the request and the responce Object
                        System.out.println("Errors occurred are: " + new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Cound not create the employee");
                            })
                    .toEntity(new ParameterizedTypeReference<>() {});

            //we can also get the Headers, NOT RECOMMENDED
            System.out.println(employeeDTOApiResponse.getHeaders());

            return employeeDTOApiResponse.getBody().getData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
