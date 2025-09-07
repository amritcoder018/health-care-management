package com.amritsolution.model.db;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document
@Data
public class DoctorToken {
    @MongoId
    private String id;
    private String doctorId;
    private int tokenBooked;
    private LocalDate date;
    private boolean doctorAvailableToday;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
