package com.amritsolution.model.db;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class TimeSlot {
    LocalDateTime startTime;
    LocalDateTime endTime;
}
