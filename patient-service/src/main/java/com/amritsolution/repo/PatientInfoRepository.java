package com.amritsolution.repo;

import com.amritsolution.model.db.PatientInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientInfoRepository extends MongoRepository<PatientInfo,String> {
    boolean existByPatientId(String patientId);
    Optional<PatientInfo> findByPatientId(String patientId);
    void deleteByPatientId(String patientId);
    void deleteByUsername(String username);
}
