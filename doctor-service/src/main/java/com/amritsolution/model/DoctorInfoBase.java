package com.amritsolution.model;

import lombok.Data;

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
    private String experience;
}
