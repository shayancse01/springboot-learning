package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    @ToString.Exclude
    @JsonIgnore
    private Patient patient; //Owning side

    @ManyToOne(fetch = FetchType.LAZY) //To avoid the JOIN Column with doctor
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Doctor doctor; //Owning side

}
