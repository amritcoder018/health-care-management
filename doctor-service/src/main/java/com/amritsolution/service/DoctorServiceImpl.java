package com.amritsolution.service;

import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.repository.DoctorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorInfoRepository repository;
    @Override
    public ResponseEntity<DoctorInfo> fetchDoctorProfile(String doctorId) {
        return repository.findByDoctorId(doctorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<List<DoctorInfo>> fetchAllDoctorsProfiles() {

        return ResponseEntity.ok().body(repository.findAll());
    }

    @Override
    public ResponseEntity<?> addDoctorProfile(DoctorInfo doctorInfo) {
        DoctorInfo doctorInfo1=repository.save(doctorInfo);
        return Objects.equals(doctorInfo1.getDoctorId(), doctorInfo.getDoctorId()) ?ResponseEntity.ok(doctorInfo.getDoctorId()):ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),"cant update the doctorId:"+doctorInfo.getDoctorId())).build();
    }

    @Override
    public ResponseEntity<?> addAllDoctorProfile(List<DoctorInfo> doctorInfos) {
        List<String> doctorIds=repository.saveAll(doctorInfos).stream().map(DoctorInfo::getDoctorId).toList();
        return Objects.equals(doctorInfos.size(),doctorIds.size()) ?ResponseEntity.ok(doctorIds):ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),"cant update the doctorId:"+doctorIds)).build();
    }

    @Override
    public ResponseEntity<?> updateDoctorProfile(DoctorInfo doctorInfo) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteDoctorProfile(String doctorId) {
        return null;
    }
}
