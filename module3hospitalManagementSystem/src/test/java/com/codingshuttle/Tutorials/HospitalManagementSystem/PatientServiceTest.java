package com.codingshuttle.Tutorials.HospitalManagementSystem;

import com.codingshuttle.Tutorials.HospitalManagementSystem.dto.BloodGroupStats;
import com.codingshuttle.Tutorials.HospitalManagementSystem.dto.CPatientInfo;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest //To make this class as test class
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void testPatient() {
        //List<Patient> patientList = patientRepository.findAll();

        //List<IPatientInfo> patientList = patientRepository.getAllPatientsInfo(); //This is for the interface
        //List<CPatientInfo> patientList = patientRepository.getAllPatientsInfoConcrete();

//        List<BloodGroupStats> patientList = patientRepository.getBloodGroupStats();
//
//        for (var p : patientList) {
//            System.out.println("Blood Group Type: " + p.getBloodGroupType());
//            System.out.println("Count: " + p.getCount());
//            System.out.println("----------------------------------------");
//        }

        int rowsAffected = patientRepository.updatePatientNameWithId("Shayan Chakraborty",1L);
        System.out.println(rowsAffected);
    }
}
