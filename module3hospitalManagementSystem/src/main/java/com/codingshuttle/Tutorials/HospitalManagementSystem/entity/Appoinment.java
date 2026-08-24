package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appoinment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appoinmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne // Many Appoinment to one patient
    @JoinColumn(nullable = false) //This means patient is required whenever we create an appoinment in the Data Base
    private Patient patient; //Owning side

    @ManyToOne
    @JoinColumn(nullable = false)
    private Doctor doctor; //Owning side

}
