package com.codingshuttle.Tutorials.HospitalManagementSystem;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Appoinment;
import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Insurance;
import com.codingshuttle.Tutorials.HospitalManagementSystem.service.AppoinmentService;
import com.codingshuttle.Tutorials.HospitalManagementSystem.service.InsuranceService;
import com.codingshuttle.Tutorials.HospitalManagementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private PatientService patientService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppoinmentService appoinmentService;

    @Test
    public void testAssignInsuranceToPatient() {
        //Task: create a insurance and add that to a patient
        Insurance insurance = Insurance.builder()
                .provider("HDFC Ergo")
                .policyNumber("HDFC_23G")
                .validUntil(LocalDate.of(2030, 1, 1))
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);
        System.out.println(updatedInsurance);

        patientService.deletePatient(1L);
    }

    @Test
    public void testCreateAppointment() {
        Appoinment appoinment = Appoinment.builder()
                .appoinmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 12))
                .reason("Cancer")
                .build();

        var updatedAppoinment = appoinmentService.createANewAppoinment(appoinment, 1L, 2L);
        System.out.println(updatedAppoinment);

        patientService.deletePatient(1L); //If we are deleting the patient, Does it also delete the appoinment as well
    }
}
