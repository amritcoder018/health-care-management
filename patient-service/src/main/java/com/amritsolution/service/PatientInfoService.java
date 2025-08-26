package com.amritsolution.service;

import com.amritsolution.model.db.PatientInfo;

public interface PatientInfoService {
    void addPatientData(PatientInfo patientInfo);
    void updatePatientData(PatientInfo patientInfo);
    PatientInfo getPatientInfoByPatientId(String id);
    PatientInfo getPatientInfoByUsername(String username);
    void deletePatientData(PatientInfo patientInfo);
    void deletePatientDataById(String id);
    void deletePatientDataByUsername(String username);
}
