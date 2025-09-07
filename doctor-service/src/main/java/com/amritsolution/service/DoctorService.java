package com.amritsolution.service;

import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.model.dto.AppointmentDTO;
import com.amritsolution.model.dto.DoctorDisplayDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    ResponseEntity<DoctorDisplayDTO> fetchDoctorDtoForUI(String doctorId);
    ResponseEntity<Page<DoctorDisplayDTO>> fetchAllDoctorDtosForUI(int page,int size);
    ResponseEntity<DoctorInfo> fetchDoctorProfile(String doctorId);
    ResponseEntity<List<DoctorInfo>> fetchAllDoctorsProfiles();
    ResponseEntity<?> addDoctorProfile(DoctorInfo doctorInfo);
    ResponseEntity<?> addAllDoctorProfile(List<DoctorInfo> doctorInfos);
    ResponseEntity<?> updateDoctorProfile(DoctorInfo doctorInfo);
    ResponseEntity<?> deleteDoctorProfile(String doctorId);
    ResponseEntity<?> requestAppointment(AppointmentDTO  appointmentDTO);
}
