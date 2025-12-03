package com.amritsolution.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document("patientUser")
public class PatientUser {
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
}
