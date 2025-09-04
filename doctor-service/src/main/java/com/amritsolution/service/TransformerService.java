package com.amritsolution.service;

import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.model.dto.DoctorDisplayDTO;

import javax.print.Doc;
import java.util.List;

public interface TransformerService {
    DoctorDisplayDTO transformToDoctorDTO(DoctorInfo doctorInfo);
    List<DoctorDisplayDTO> transformToDoctorDTOList(List<DoctorInfo> doctorInfoList);
}
