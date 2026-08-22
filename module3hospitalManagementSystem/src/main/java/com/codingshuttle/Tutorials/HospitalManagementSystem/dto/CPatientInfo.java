package com.codingshuttle.Tutorials.HospitalManagementSystem.dto;

import lombok.Data;

@Data //create getters and setters
public class CPatientInfo {  //this is the Nice POJO class that we are creating
    private final Long id; //As we are adding final keyword here, it will create required args constructor for us, Otherwise we have to put no args and all args constructor here.
    private final String name;
}
