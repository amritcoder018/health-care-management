package com.amritsolution.service;

import com.amritsolution.StaticFields;
import com.amritsolution.exception.AppointmentException;
import com.amritsolution.exception.ErrorResponse;
import com.amritsolution.model.BookingStatus;
import com.amritsolution.model.db.Appointment;
import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.model.db.DoctorToken;
import com.amritsolution.model.dto.AppointmentDTO;
import com.amritsolution.model.dto.DoctorDisplayDTO;
import com.amritsolution.model.mapper.AppointmentMapper;
import com.amritsolution.repository.AppointmentRepository;
import com.amritsolution.repository.DoctorInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorInfoRepository repository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    MongoUpdateService mongoUpdateService;
    @Autowired
    private TransformerService transformerService;

    @Override
    public ResponseEntity<DoctorDisplayDTO> fetchDoctorDtoForUI(String doctorId) {
        return repository.findByDoctorId(doctorId)
                .map(transformerService::transformToDoctorDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public Page<DoctorDisplayDTO> fetchAllDoctorDtosForUI(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        log.info("service method to fetch and paginate the doctorinfo");
        return repository.findAll(pageable).map(transformerService::transformToDoctorDTO);
       }

    @Override
    public ResponseEntity<DoctorInfo> fetchDoctorProfile(String doctorId) {
        return repository.findByDoctorId(doctorId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<List<DoctorInfo>> fetchAllDoctorsProfiles() {

        return ResponseEntity.ok().body(repository.findAll());
    }

    @Override
    public ResponseEntity<?> addDoctorProfile(DoctorInfo doctorInfo) {
        DoctorInfo doctorInfo1=repository.save(doctorInfo);
        return Objects.equals(doctorInfo1.getDoctorId(), doctorInfo.getDoctorId()) ?ResponseEntity.ok(doctorInfo.getDoctorId()):ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),"cant update the doctorId:"+doctorInfo.getDoctorId())).build();
    }

    @Override
    public ResponseEntity<?> addAllDoctorProfile(List<DoctorInfo> doctorInfos) {
        List<String> doctorIds=repository.saveAll(doctorInfos).stream().map(DoctorInfo::getDoctorId).toList();
        return Objects.equals(doctorInfos.size(),doctorIds.size()) ?ResponseEntity.ok(doctorIds):ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),"cant update the doctorId:"+doctorIds)).build();
    }

    @Override
    public ResponseEntity<?> updateDoctorProfile(DoctorInfo doctorInfo) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteDoctorProfile(String doctorId) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> requestAppointment(AppointmentDTO appointmentDTO) {
        LocalDateTime now = LocalDateTime.now();
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointment.setAppointmentId(UUID.randomUUID().toString());
        appointment.setPatientId(appointmentDTO.getPatientId());
        appointment.setDoctorId(appointmentDTO.getDoctorId());
        appointment.setCurrentStatus(BookingStatus.PROCESSING_REQUEST);
        appointment.setCreated_at(now);
        appointment.setUpdated_at(now);
        DoctorDisplayDTO doctorDisplayDTO;
        log.info("the appointment is saved in db" + appointment);
        try {
            DoctorToken doctorToken = mongoUpdateService.updateDoctorToken(appointmentDTO.getDoctorId(), appointmentDTO.getBookedFor());
            DoctorInfo doctorInfo=repository.findByDoctorId(appointmentDTO.getDoctorId()).get();
            doctorDisplayDTO=transformerService.transformToDoctorDTO(doctorInfo);
            BookingStatus bs;
            if (Objects.nonNull(doctorToken)) {
                int tokenNo=doctorToken.getTokenBooked()+1;
                bs=BookingStatus.PROCESSING_PAYMENT;
                appointment.setCurrentStatus(bs);
                doctorDisplayDTO.setTokenAvailable(tokenNo< StaticFields.TOTAL_TOKEN);
                appointment.setTokenNo(tokenNo + "");
                appointment.setUpdated_at(LocalDateTime.now());
                doctorDisplayDTO.setBookingStatus(bs);
                log.info("appointment under process {}",appointment);

            }

        }catch (AppointmentException e)
        {
            appointment.setCurrentStatus(e.getBookingStatus());
            appointment.setUpdated_at(LocalDateTime.now());
            return ResponseEntity.badRequest().body(ErrorResponse.builder()
                            .bookingStatus(e.getBookingStatus())
                            .internalStatusCode(5001)
                            .message(e.getMessage())
                            .doctorId(e.getDoctorId())
                    .build());

        }finally {

            appointmentRepository.save(appointment);
        }

        return ResponseEntity.ok(doctorDisplayDTO);
    }
}
