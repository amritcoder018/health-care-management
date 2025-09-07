package com.amritsolution.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private String doctorId;
    private String patientId;
    private LocalDate bookedFor;
}
