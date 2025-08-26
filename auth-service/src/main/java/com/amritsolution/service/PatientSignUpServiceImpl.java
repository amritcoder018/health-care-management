package com.amritsolution.service;

import com.amritsolution.model.dto.PatientInfoDTO;
import com.amritsolution.model.dto.PatientSignUpDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientSignUpServiceImpl implements PatientSignUpService{
    @Override
    public ResponseEntity<?> registerNewPatient(PatientSignUpDTO patientSignUpDTO) {
        return transferPatientInfoDTO(patientSignUpDTO);
    }
    private ResponseEntity<?> transferPatientInfoDTO(PatientInfoDTO patientInfoDTO)
    {
        return null;
    }
}
