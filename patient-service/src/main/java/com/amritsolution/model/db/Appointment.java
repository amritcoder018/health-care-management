package com.amritsolution.model.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @MongoId
    private String id;
    private String appointmentId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
