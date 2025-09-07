package com.amritsolution.service;

import com.amritsolution.exception.AppointmentException;
import com.amritsolution.model.db.DoctorToken;

import java.time.LocalDate;

public interface MongoUpdateService {
    DoctorToken updateDoctorToken(String doctorId, LocalDate date)throws AppointmentException;
}
