package com.codingshuttle.Tutorials.HospitalManagementSystem.repository;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}