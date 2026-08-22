package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;


import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.type.BloodgroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodgroupType bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
