package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;

    private String name;

    private String email;

    private int age;

    private String role;

    private Double salary;

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private boolean isActive;
}
