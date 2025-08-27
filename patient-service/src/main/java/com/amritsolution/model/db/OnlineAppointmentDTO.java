package com.amritsolution.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OnlineAppointmentDTO{
    private TimeSlot consultationTimeSlot;
    private String platform;
    private boolean meetingScheduled;
}
