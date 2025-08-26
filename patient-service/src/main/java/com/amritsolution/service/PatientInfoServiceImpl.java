package com.amritsolution.service;

import com.amritsolution.model.db.PatientInfo;
import com.amritsolution.repo.PatientInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PatientInfoServiceImpl implements PatientInfoService{
    @Autowired
    PatientInfoRepository repository;

    @Override
    public void addPatientData(PatientInfo patientInfo) {
        if(!repository.existByPatientId(patientInfo.getPatientId())) {
            repository.save(patientInfo);
        }
        else{
            //throw exception if already present
        }
    }

    @Override
    public void updatePatientData(PatientInfo patientInfo) {
        if(repository.existByPatientId(patientInfo.getPatientId()))
        {
            repository.save(patientInfo);
        }else {
            //need to throw exception if not present
        }
        }

    @Override
    public PatientInfo getPatientInfoByPatientId(String patientId) {
        PatientInfo patientInfo=repository.findByPatientId(patientId).get();//add exception if not present;
        return patientInfo;
    }

    @Override
    public PatientInfo getPatientInfoByUsername(String username) {
        //add exception if not present;
        return repository.findByPatientId(username).get();
    }

    @Override
    public void deletePatientData(PatientInfo patientInfo) {
        repository.delete(patientInfo);
    }

    @Override
    public void deletePatientDataById(String id) {
        repository.deleteByPatientId(id);
    }

    @Override
    public void deletePatientDataByUsername(String username) {
        repository.deleteByUsername(username);
    }
}
