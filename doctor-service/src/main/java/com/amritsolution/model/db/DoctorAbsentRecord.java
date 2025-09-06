package com.amritsolution.model.db;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Document
public class DoctorAbsentRecord {
    @MongoId
    private String id;
    private String doctorId;
    private Date absentDate;
    private String absentReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
