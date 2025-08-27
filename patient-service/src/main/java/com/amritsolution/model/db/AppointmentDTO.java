package com.amritsolution.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AppointmentDTO {
    private String doctorId;
    private String patientId;
    private String patientUsername;
    private String doctorUsername;
    private String doctorEmail;
    private String healthIssue;
    private boolean isOnline;
    private OfflineAppointmentDTO offlineAppointment;
    private OnlineAppointmentDTO onlineAppointment;

}
