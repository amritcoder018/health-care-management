package com.amritsolution.service;

import com.amritsolution.model.db.Appointment;
import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.model.dto.AppointmentDTO;
import com.amritsolution.model.dto.DoctorDisplayDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransformerServiceImpl implements TransformerService {
    @Override
    public DoctorDisplayDTO transformToDoctorDTO(DoctorInfo doctorInfo) {
        log.info("converting doctorInfo for doctorId:{}",doctorInfo.getDoctorId());
        DoctorDisplayDTO doctorDisplayDTO = new DoctorDisplayDTO();
        doctorDisplayDTO.setDoctorId(doctorInfo.getDoctorId());
        doctorDisplayDTO.setBranch(doctorInfo.getBranch());
        doctorDisplayDTO.setEmail(doctorInfo.getEmail());
        doctorDisplayDTO.setExperience(doctorInfo.getExperience());
        doctorDisplayDTO.setHospital(doctorInfo.getHospital());
        doctorDisplayDTO.setFullName(doctorInfo.getFullName());
        doctorDisplayDTO.setSex(doctorInfo.getSex());
        doctorDisplayDTO.setWorkingDays(doctorInfo.getWorkingDays());
        doctorDisplayDTO.setAvailableOnline(doctorInfo.isAvailableOnline());
        doctorDisplayDTO.setMedicalSpecialty(doctorInfo.getMedicalSpecialty());
        doctorDisplayDTO.setOpdRoomNo(doctorInfo.getOpdRoomNo());

        return doctorDisplayDTO;
    }

    @Override
    public List<DoctorDisplayDTO> transformToDoctorDTOList(List<DoctorInfo> doctorInfoList) {
        return doctorInfoList.stream().map(this::transformToDoctorDTO).toList();
    }

    @Override
    public Appointment transformDTOAppointmentToDBEntity(AppointmentDTO appointmentDTO) {
        return null;
    }
}
