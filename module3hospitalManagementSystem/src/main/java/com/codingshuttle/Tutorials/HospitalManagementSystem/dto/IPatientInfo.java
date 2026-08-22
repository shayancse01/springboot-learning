package com.codingshuttle.Tutorials.HospitalManagementSystem.dto;

public interface IPatientInfo {

    Long getId();
    String getName();
    String getEmail();

    //This interface only having these getter methods, not any function or something so that we can modify the data as well
}
