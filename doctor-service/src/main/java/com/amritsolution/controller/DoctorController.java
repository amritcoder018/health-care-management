package com.amritsolution.controller;

import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity<?> addDoctorProfile(@RequestBody DoctorInfo doctorInfo)
    {
        return doctorService.addDoctorProfile(doctorInfo);
    }
    @PostMapping("/addAll")
    public ResponseEntity<?> addAllDoctorProfile(@RequestBody List<DoctorInfo> doctorInfos)
    {
        return doctorService.addAllDoctorProfile(doctorInfos);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoctorProfile()
    {
        return doctorService.fetchAllDoctorsProfiles();
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDoctorProfile(@PathVariable String id)
    {
        return doctorService.fetchDoctorProfile(id);
    }
}
