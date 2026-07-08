package com.codingshuttle.shayan.module2restApi.dto;

import com.codingshuttle.shayan.module2restApi.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//This class will contain what Employee have and what Employee can do
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {  //basically it is the POJO class

    private Long id;

    @NotBlank(message = "name of the employee cann't be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range: [3, 10]")
    private String name;

    @Email(message = "Provide a valid email")
    private String email;

    @Max(value = 80, message = "Age cann't be greater than 80")
    @Min(value = 18, message = "Employee age cann't be less than 18")
    private int age;

    @NotBlank(message = "role cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$", message = "rolse of message can be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role; //it can take either ADMIN or USER, nothing else

    @Positive(message = "salary should be positive")
    @Digits(integer = 6, fraction = 2)
    @NotNull
    private Double salary;

    private LocalDate dateOfJoining;

    @JsonIgnore
    @AssertTrue(message = "isActive filed should be true")
    private boolean isActive;

    @JsonGetter("isActive")
    public boolean getIsActive() {
        return isActive;
    }

    @JsonSetter("isActive")
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
