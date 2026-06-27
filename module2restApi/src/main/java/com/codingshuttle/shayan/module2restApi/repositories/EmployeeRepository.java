package com.codingshuttle.shayan.module2restApi.repositories;

import com.codingshuttle.shayan.module2restApi.entities.EmployeeEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    //List<EmployeeEntity> findByname(String name);
}
