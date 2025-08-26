package com.amritsolution.service;

import com.amritsolution.model.dto.PatientSignUpDTO;
import org.springframework.http.ResponseEntity;

public interface PatientSignUpService {

    ResponseEntity<?> registerNewPatient(PatientSignUpDTO patientSignUpDTO);
}
