package com.amritsolution.model.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientInfoDTO {
    String name;
    String mobileNo;
    String nationality;
    DOB dob;
    Address permanentAddress;
    Address currentAddress;
    String emailId;
    String adhaarNo;
    String bloodGrp;
    String allergies;
    boolean isDiabetic;
}
