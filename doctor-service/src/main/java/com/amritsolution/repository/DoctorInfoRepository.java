package com.amritsolution.repository;

import com.amritsolution.model.db.DoctorInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface DoctorInfoRepository extends MongoRepository<DoctorInfo,Integer>
{
    Optional<DoctorInfo> findByDoctorId(String doctorId);
    boolean existsByDoctorIdAndWorkingDaysContaining(String doctorId, DayOfWeek day);
}
