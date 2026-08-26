package com.codingshuttle.Tutorials.HospitalManagementSystem.repository;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}