package com.amritsolution.model.dto;

import com.amritsolution.model.BookingStatus;
import com.amritsolution.model.DoctorInfoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorDisplayDTO extends DoctorInfoBase {
    private boolean tokenAvailable;
    private BookingStatus bookingStatus;
    private LocalDateTime bookedOn;
    private String tokenNo;
}
