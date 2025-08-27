package com.amritsolution.service;

import com.amritsolution.model.db.AppointmentDTO;
import com.amritsolution.model.db.OfflineAppointmentDTO;
import com.amritsolution.model.db.OnlineAppointmentDTO;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity<?> bookAppointment(AppointmentDTO appointmentDTO);
    void makeAnAppointmentOnline(AppointmentDTO appointmentDTO);
    void makeAnAppointmentOffline(AppointmentDTO appointmentDTO);
    void cancelOnlineAppointment(AppointmentDTO appointmentDTO);
    void cancelOfflineAppointment(AppointmentDTO appointmentDTO);
    void cancelAppointmentById(String appointmentId);
}
