package com.amritsolution.model.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class PatientInfo {
    @Id
    private String id;
    private String patientId;
    private String name;
    private String mobile;
    private String nationality;
    private DOB dob;
    private Address permanentAddress;
    private Address currentAddress;
    private String email;
    private String adhaarNo;
    private String bloodGrp;
    private String allergies;
    private boolean isDiabetic;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
