package com.codingshuttle.shayan.module2restApi.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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
    private String name;
    private String email;
    private int age;
    private LocalDate dateOfJoining;
    @JsonIgnore
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
