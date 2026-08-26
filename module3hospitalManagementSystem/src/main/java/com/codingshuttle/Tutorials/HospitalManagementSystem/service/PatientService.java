package com.codingshuttle.Tutorials.HospitalManagementSystem.service;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.Patient;
import com.codingshuttle.Tutorials.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public void testPatientTransaction() {

        // First call:
        // Hibernate queries the database for Patient with ID = 1.
        // The returned Patient object is stored in the persistence context
        // (the first-level cache) associated with the current transaction.
        Patient p1 = patientRepository.findById(1L).orElseThrow();

        // Second call:
        // Hibernate checks the persistence context first.
        // Patient with ID = 1 is already managed and present there,
        // so Hibernate returns the SAME Java object instead of executing
        // another SELECT query against the database.
        Patient p2 = patientRepository.findById(1L).orElseThrow();

        System.out.println(p1 + " " + p2);

        // Both variables refer to the exact same Java object in memory.
        // Therefore, this prints true.
        System.out.println(p1 == p2);

        /*
         * Why does this happen?
         *
         * @Transactional defines a transaction boundary around this method.
         * During this transaction, JPA/Hibernate uses a persistence context
         * (first-level cache) associated with the transaction.
         *
         * The persistence context follows the identity rule:
         *
         *      One database entity identity (ID) -> One managed Java object
         *
         * So after Patient(1L) is loaded for the first time, Hibernate keeps
         * that managed entity in the persistence context.
         *
         * When findById(1L) is called again, Hibernate sees that Patient(1L)
         * is already present in the persistence context and returns the same
         * managed object.
         *
         * Therefore:
         *
         *      p1 == p2  -> true
         *
         * This is also why we don't get two separate Java objects
         * representing the same Patient within the same persistence context.
         */

        p1.setName("Updated Name");
        // p1 is a managed entity, so Hibernate automatically tracks this change.
        // During transaction commit/flush, Hibernate's dirty checking detects the change
        // and automatically executes an UPDATE query and update inside the database. No save() is required.
    }

    //Now we will delete a Patient as well as the insurance associated with it
    @Transactional
    public void deletePatient(Long patientId) {

        patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId);

        //The insurance will also get deleted as we have set cascade type All
        //So we are deleting the parent, The child will also be deleted
    }
}
