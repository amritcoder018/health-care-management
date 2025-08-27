package com.amritsolution.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfflineAppointmentDTO{
    private TimeSlot timeSlot;
    private String roomNo;
}
