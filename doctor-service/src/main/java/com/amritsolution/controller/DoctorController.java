package com.amritsolution.controller;

import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.repository.UpdateDoctorInfo;
import com.amritsolution.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UpdateDoctorInfo  updateDoctorInfo;
    @PostMapping("/add")
    public ResponseEntity<?> addDoctorProfile(@RequestBody DoctorInfo doctorInfo)
    {
        return doctorService.addDoctorProfile(doctorInfo);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDoctorProfile(@RequestParam String doctorId,@RequestBody Set<DayOfWeek> workingDays)
    {
//        Set<DayOfWeek> workingDaysSet=workingDays.stream()
//                .map(String::toUpperCase)
//                .map(DayOfWeek::valueOf)
//                .collect(Collectors.toSet());
        updateDoctorInfo.updateSpecializationByDoctorId(doctorId,workingDays);
        return ResponseEntity.ok().build();
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
    @GetMapping("/wakeup")
    public Mono<String> wakeup() {
        return Mono.just("doctor-service up");
    }
}
