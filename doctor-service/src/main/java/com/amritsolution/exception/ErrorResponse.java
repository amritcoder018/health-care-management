package com.amritsolution.exception;

import com.amritsolution.model.BookingStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int internalStatusCode;
    private BookingStatus bookingStatus;
    private String doctorId;
    private String message;
    private Exception exception;
}
