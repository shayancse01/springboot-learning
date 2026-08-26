package com.codingshuttle.Tutorials.HospitalManagementSystem.repository;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {

}