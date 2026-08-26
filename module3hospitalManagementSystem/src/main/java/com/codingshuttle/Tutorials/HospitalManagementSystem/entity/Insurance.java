package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String policyNumber;

    @Column(nullable = false, length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp //It will make ensure this insurance will filled with the correct date
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //By directional mapping
    @OneToOne(mappedBy = "insurance") //mappedBy is used for the inverse mapping, Now Insurance will not have any column like Patient
    private Patient patient; //Inverse side

}
