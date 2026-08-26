package com.codingshuttle.Tutorials.HospitalManagementSystem.repository;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}