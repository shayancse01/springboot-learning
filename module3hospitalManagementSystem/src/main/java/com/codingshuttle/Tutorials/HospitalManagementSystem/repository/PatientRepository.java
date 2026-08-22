package com.codingshuttle.Tutorials.HospitalManagementSystem.repository;

import com.codingshuttle.Tutorials.HospitalManagementSystem.dto.BloodGroupStats;
import com.codingshuttle.Tutorials.HospitalManagementSystem.dto.CPatientInfo;
import com.codingshuttle.Tutorials.HospitalManagementSystem.dto.IPatientInfo;
import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //List<Patient> findByEmailContaining(String d);

    @Query("select p.id as id,p.name as name, p.email as email from Patient p")
    List<IPatientInfo> getAllPatientsInfo();
    //The list coming from here is read only(as we are using interface) we cannot modify it
    //Here we are using IPatientInfo interface not a dto object, so Hibernate is creating a proxy object for us

    @Query("select new com.codingshuttle.Tutorials.HospitalManagementSystem.dto.CPatientInfo(p.id ,p.name)  from Patient p")
    List<CPatientInfo> getAllPatientsInfoConcrete();
    //as it's a class we can modify the list


    //Now we will see how projection will work in case of aggregate query
    @Query("select new com.codingshuttle.Tutorials.HospitalManagementSystem.dto.BloodGroupStats(p.bloodGroup, COUNT(p))" +
            "from Patient p group by p.bloodGroup order by COUNT(p) DESC")
    List<BloodGroupStats> getBloodGroupStats();
    //count is not present in the database

    //Now will do updation in the database with the help of transactional
    @Transactional // Atomicity will be performed here
    @Modifying // so that the JpaRepo know that we are updating thing in the database
    @Query("UPDATE Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);

}
