package com.amritsolution.controller;

import com.amritsolution.model.dto.AppointmentDTO;
import com.amritsolution.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ui")
@Slf4j
public class UIController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/book")
    public ResponseEntity<?> requestAppointment(@RequestBody AppointmentDTO appointmentDTO)
    {
        return doctorService.requestAppointment(appointmentDTO);

    }

}
