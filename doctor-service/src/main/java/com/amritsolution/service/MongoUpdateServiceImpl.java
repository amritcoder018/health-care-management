package com.amritsolution.service;

import com.amritsolution.StaticFields;
import com.amritsolution.exception.AppointmentException;
import com.amritsolution.model.BookingStatus;
import com.amritsolution.model.db.DoctorToken;
import com.amritsolution.repository.DoctorTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

@Repository
@Slf4j
public class MongoUpdateServiceImpl implements MongoUpdateService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DoctorTokenRepository repository;
    @Override
    public DoctorToken updateDoctorToken(String doctorId,LocalDate date)throws AppointmentException {
        Query query = new Query();
        Query query2 = new Query();
        Date startOfDay = Date.from(date.atStartOfDay(ZoneOffset.UTC).toInstant());
        Date endOfDay = Date.from(date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant());

        query.addCriteria(Criteria.where("date").gte(startOfDay).lt(endOfDay));

        query.addCriteria(Criteria.where("doctorId").is(doctorId)
                .and("tokenBooked").lt(StaticFields.TOTAL_TOKEN)); // only if tokens remain
        query2.addCriteria(Criteria.where("doctorId").is(doctorId)

                .and("date").gte(startOfDay).lt(endOfDay));
        Update update = new Update().inc("availableToken", 1);

        DoctorToken result = mongoTemplate.findAndModify(query, update, DoctorToken.class);
        log.info("update doctorToken {} ", result);
        DoctorToken withoutToken=mongoTemplate.findOne(query2,DoctorToken.class);

        if(Objects.isNull(result)&&Objects.isNull(withoutToken)){
            log.info("appointment failed due to un under doctor {}",doctorId);
throw new AppointmentException("Failed due to doctor is unavailable at this date",doctorId,BookingStatus.FAILED_DUE_TO_DOCTOR_NOT_AVAILABLE);
        }
        else if(Objects.isNull(result)&&Objects.nonNull(withoutToken))
        {
            log.info("appointment failed due to no token doctor {}",doctorId);
            throw new AppointmentException("Failed due to no token left for booking appointment",doctorId,BookingStatus.FAILED_DUE_TO_UNAVAILABLE_TOKEN);

        }
        return result;
    }
}
