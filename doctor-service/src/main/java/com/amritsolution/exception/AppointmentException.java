package com.amritsolution.exception;

import com.amritsolution.model.BookingStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
public class AppointmentException extends RuntimeException {
    private String doctorId;
    private BookingStatus bookingStatus;

    public AppointmentException(String message, String doctorId, BookingStatus bookingStatus) {
        super(message);
        this.doctorId = doctorId;
        this.bookingStatus = bookingStatus;
    }

    public AppointmentException() {
    }
}
