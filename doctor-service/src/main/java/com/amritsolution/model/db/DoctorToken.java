package com.amritsolution.model.db;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.Date;

@Document
@Data
public class DoctorToken {
    @MongoId
    private String id;
    private String doctorId;
    private int availableToken;
    private LocalDate date;
    private boolean doctorAvailableToday;
}
