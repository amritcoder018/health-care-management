package com.amritsolution.model.db;

import com.amritsolution.model.DoctorInfoBase;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class DoctorInfo extends DoctorInfoBase {
    @Id
   private String id;
   private String contactNo;
   private int age;
   private String panNo;
   private String adhaarNo;
}
