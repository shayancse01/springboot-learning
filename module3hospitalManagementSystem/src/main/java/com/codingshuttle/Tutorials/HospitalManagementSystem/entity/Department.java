package com.codingshuttle.Tutorials.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true, length = 100)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false) //Nullable = false means department cannot exists without a head of the Department
    private Doctor headDoctor; //Owning side,One Department will have One Head Doctor

    @ManyToMany //Whenever we will have many to many mapping then the hibernate will create the join "TABLE" for us
    private Set<Doctor> doctors = new HashSet<>();
}
