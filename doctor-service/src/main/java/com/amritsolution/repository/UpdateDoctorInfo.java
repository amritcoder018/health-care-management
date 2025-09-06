package com.amritsolution.repository;

import com.amritsolution.model.db.DoctorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Set;

@Repository
public class UpdateDoctorInfo {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateSpecializationByDoctorId(String doctorId, Set<DayOfWeek> workingDays) {
        Query query = new Query(Criteria.where("doctorId").is(doctorId));
        Update update = new Update().set("workingDays", workingDays);
        mongoTemplate.updateFirst(query, update, DoctorInfo.class);
    }
}
