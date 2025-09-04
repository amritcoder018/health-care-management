package com.amritsolution.model.dto;

import com.amritsolution.model.DoctorInfoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorDisplayDTO extends DoctorInfoBase {
    private boolean tokenAvailable;
}
