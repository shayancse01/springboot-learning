package com.codingshuttle.Tutorials.HospitalManagementSystem.service;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Insurance;
import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Patient;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.InsuranceRepository;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional //Now we will have One persistent context for the whole transaction
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {
    //so currently the patient is inside the database, but as we are creating the insurance, so it is not inside our database
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        //As patient will have the insurance_id column OR Patient holds the FK for insurance
        //Here we have to convert Insurance from Transient state to persistent state, for this --> will use cascading
        patient.setInsurance(insurance); //Dirty patient, Hibernate will automatically update it while doing dirty checking(As we have used transactional here)
        //Only this line will take care of the relationship

        insurance.setPatient(patient); //Optional, just to maintain the By directional consistency

        return insurance;
    }

    //It will remove the insurance associated with a patient
    @Transactional
    public Patient removeInsuranceOfAPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(null);

        return patient;
    }

    @Transactional
    public Insurance updateInsuranceOfAPatient(Insurance insurance, Long patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        return insurance;
        //It will also delete the previous insurance and assign the new insurance to the patient
    }

}
