package com.amritsolution.model.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfo {
    @Id
    String id;
    String doctorId;
    String fullName;
    String contactNo;
    String panNo;
    String adhaarNo;
    String medicalSpecialty;
    String sex;
    int totalExperienceYear;
    boolean availableForOnline;
}
