package com.codingshuttle.Tutorials.HospitalManagementSystem.dto;

import com.codingshuttle.Tutorials.HospitalManagementSystem.entity.type.BloodgroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodgroupType bloodGroupType;
    private final Long count;
}
