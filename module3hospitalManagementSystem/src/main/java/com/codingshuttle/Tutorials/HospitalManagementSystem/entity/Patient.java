package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;


import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.type.BloodgroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne //One To One annotation automatically make Unique = true
    @JoinColumn(name = "patient_insurance", unique = true) //Only use join column in the owning side, Now the name of this column will be patient_insurance
    private Insurance insurance; //Owning side, Patient will now have the insurance column.

    //As One patient will have multiple appoinments
    @OneToMany(mappedBy = "patient")
    private Set<Appoinment> appoinments = new HashSet<>(); //Inverse side
    //In set the Data is present in a random order, so we can also create a List instead

}
