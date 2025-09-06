package com.amritsolution.model;

import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Data
public class DoctorInfoBase {
    private String doctorId;
    private String fullName;
    private String email;
    private String sex;
    private String opdRoomNo;
    private String hospital;
    private String branch;
    private boolean availableOnline;
    private String medicalSpecialty;
    private Set<DayOfWeek> workingDays;
    private String experience;
}
