package com.amritsolution.controller;

import com.amritsolution.model.dto.AppointmentDTO;
import com.amritsolution.model.dto.DoctorDisplayDTO;
import com.amritsolution.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ui")
@Slf4j
public class UIController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/getAll")
    public ResponseEntity<Page<DoctorDisplayDTO>> getDoctorDTOs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        log.info("pagination request from api");
       return doctorService.fetchAllDoctorDtosForUI(page, size);
    }
    @PostMapping("/book")
    public ResponseEntity<?> requestAppointment(@RequestBody AppointmentDTO appointmentDTO)
    {
        return doctorService.requestAppointment(appointmentDTO);

    }

}
