package com.amritsolution.model.mapper;

import com.amritsolution.model.db.Appointment;
import com.amritsolution.model.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointment toEntity(AppointmentDTO appointmentDTO);
    AppointmentDTO toDTO(Appointment appointment);
    List<AppointmentDTO> toDTO(List<Appointment> appointments);
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", ignore = true)
//    @Mapping(target = "currentStatus", ignore = true)
//    @Mapping(target = "appointmentId",ignore = true)
//    @Mapping(target = "tokenNo",ignore = true)
    void updateFromDto(AppointmentDTO dto, @MappingTarget Appointment entity);
}
