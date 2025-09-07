package com.amritsolution.cofig;

import com.amritsolution.StaticFields;
import com.amritsolution.model.BookingStatus;
import com.amritsolution.model.DoctorInfoBase;
import com.amritsolution.model.db.Appointment;
import com.amritsolution.model.db.DoctorInfo;
import com.amritsolution.model.db.DoctorToken;
import com.amritsolution.repository.AppointmentRepository;
import com.amritsolution.repository.DoctorAbsentRepository;
import com.amritsolution.repository.DoctorInfoRepository;
import com.amritsolution.repository.DoctorTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
@Slf4j
public class TokenAvailableSchedular {
    @Autowired
    private DoctorTokenRepository tokenRepository;
    @Autowired
    private DoctorInfoRepository infoRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorAbsentRepository absentRepository;

    @Scheduled(fixedRate = 100000)
    private void tokenCreationScheduledTask()throws Exception {
        infoRepository.findAll().stream()
                .filter(this::checkEachDoctorAvailableToday)
                .map(DoctorInfoBase::getDoctorId)
                .forEach(this::addTodaysAppointmentToken);
        appointmentRepository.findByCurrentStatus(BookingStatus.PROCESSING_PAYMENT).stream()
                .forEach(this::updateStatusAsFailedPayment);

    }
    private boolean checkEachDoctorAvailableToday(DoctorInfo doctorInfo)
    {
        return  doctorInfo.getWorkingDays().contains(LocalDateTime.now().getDayOfWeek());
    }

    private void updateStatusAsFailedPayment(Appointment appointment)//due to timeout appointment processing payment status is set to failed
    {

        if(Duration.between(appointment.getUpdated_at(),LocalDateTime.now()).toMinutes()>1) {
            log.info("Appointment id {} is updated with failed payment status because it exceeded {} min time out period",appointment.getAppointmentId(),StaticFields.MAX_PAYMENT_TIMEOUT_IN_MIN);
            appointment.setCurrentStatus(BookingStatus.PAYMENT_FAILED);
            appointmentRepository.save(appointment);
            return;
        }
        log.info("waiting for Payment to process for appointment id {}",appointment.getAppointmentId());
    }

    private void addTodaysAppointmentToken(String doctorId)
    {
        log.info("doctorId received: {}",doctorId);
        LocalDate now = LocalDate.now();
        if(tokenRepository.existsByDoctorIdAndDate(doctorId,now)) {
            log.info("Doctor Token already exists");
            return;
        }

        DoctorToken doctorToken=new DoctorToken();
        doctorToken.setDoctorId(doctorId);
        doctorToken.setDate(now);
        log.info("Date is {}",now);
        doctorToken.setUpdatedAt(LocalDateTime.now());
        doctorToken.setCreatedAt(LocalDateTime.now());

        if(absentRepository.existsByDoctorIdAndAbsentDate(doctorId,now) ||!infoRepository.existsByDoctorIdAndWorkingDaysContaining(doctorId,now.getDayOfWeek())) {
            doctorToken.setTokenBooked(StaticFields.TOTAL_TOKEN);
            doctorToken.setDoctorAvailableToday(false);
        }else {
            doctorToken.setTokenBooked(0);
            doctorToken.setDoctorAvailableToday(true);
        }
        tokenRepository.save(doctorToken);
    }
}
