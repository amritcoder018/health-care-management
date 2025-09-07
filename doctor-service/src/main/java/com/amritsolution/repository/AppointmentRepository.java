package com.amritsolution.repository;

import com.amritsolution.model.BookingStatus;
import com.amritsolution.model.db.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {
    List<Appointment> findByCurrentStatus(BookingStatus bookingStatus);
}
