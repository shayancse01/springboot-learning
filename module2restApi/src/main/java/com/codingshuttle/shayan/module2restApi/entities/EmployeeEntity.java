package com.codingshuttle.shayan.module2restApi.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private int age;
    private LocalDate dateOfJoining;
    private double salary;
    private String role;

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
