package com.amritsolution.service;

import com.amritsolution.model.db.DoctorInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    ResponseEntity<DoctorInfo> fetchDoctorProfile(String doctorId);
    ResponseEntity<List<DoctorInfo>> fetchAllDoctorsProfiles();
    ResponseEntity<?> addDoctorProfile(DoctorInfo doctorInfo);
    ResponseEntity<?> addAllDoctorProfile(List<DoctorInfo> doctorInfos);
    ResponseEntity<?> updateDoctorProfile(DoctorInfo doctorInfo);
    ResponseEntity<?> deleteDoctorProfile(String doctorId);
}
