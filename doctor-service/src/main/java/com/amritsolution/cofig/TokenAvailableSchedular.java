package com.amritsolution.cofig;

import com.amritsolution.StaticFields;
import com.amritsolution.model.DoctorInfoBase;
import com.amritsolution.model.db.DoctorToken;
import com.amritsolution.repository.DoctorAbsentRepository;
import com.amritsolution.repository.DoctorInfoRepository;
import com.amritsolution.repository.DoctorTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@Slf4j
public class TokenAvailableSchedular {
    @Autowired
    private DoctorTokenRepository tokenRepository;
    @Autowired
    private DoctorInfoRepository infoRepository;
    @Autowired
    private DoctorAbsentRepository absentRepository;

    @Scheduled(fixedRate = 10000)
    private void tokenCreationScheduledTask()throws Exception {
        infoRepository.findAll().stream()
                .map(DoctorInfoBase::getDoctorId)
                .forEach(this::updateDoctorToken);
    }

    private void updateDoctorToken(String doctorId)
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

        if(absentRepository.existsByDoctorIdAndAbsentDate(doctorId,now) ||!infoRepository.existsByDoctorIdAndWorkingDaysContaining(doctorId,now.getDayOfWeek())) {
            doctorToken.setAvailableToken(0);
            doctorToken.setDoctorAvailableToday(false);
        }else {
            doctorToken.setAvailableToken(StaticFields.TOTAL_TOKEN);
            doctorToken.setDoctorAvailableToday(true);
        }
        tokenRepository.save(doctorToken);
    }
}
