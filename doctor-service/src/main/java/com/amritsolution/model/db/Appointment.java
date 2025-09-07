package com.amritsolution.model.db;

import com.amritsolution.model.BookingStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document
public class Appointment {
    private String id;
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private LocalDate bookedFor;
    private BookingStatus currentStatus;
    private String tokenNo;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
