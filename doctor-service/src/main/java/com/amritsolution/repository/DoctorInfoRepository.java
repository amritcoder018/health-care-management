package com.amritsolution.repository;

import com.amritsolution.model.db.DoctorInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DoctorInfoRepository extends MongoRepository<DoctorInfo,Integer>
{
    Optional<DoctorInfo> findByDoctorId(String doctorId);

}
