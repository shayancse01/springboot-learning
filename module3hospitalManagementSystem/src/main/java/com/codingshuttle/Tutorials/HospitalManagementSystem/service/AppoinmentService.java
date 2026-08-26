package com.codingshuttle.Tutorials.HospitalManagementSystem.service;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Appoinment;
import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Doctor;
import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Patient;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.AppoinmentRepository;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.DoctorRepository;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppoinmentService {

    private final AppoinmentRepository appoinmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appoinment createANewAppoinment(Appoinment appoinment, Long doctorId, Long patientId) {

        Patient patient = patientRepository.findById(doctorId).orElseThrow();
        Doctor doctor = doctorRepository.findById(patientId).orElseThrow();

        appoinment.setPatient(patient); //Appoinment contains FK of Patient and Doctor
        appoinment.setDoctor(doctor);

        appoinmentRepository.save(appoinment);

        return appoinment;
    }

}
