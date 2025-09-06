package com.amritsolution.repository;

import com.amritsolution.model.db.DoctorToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface DoctorTokenRepository extends MongoRepository<DoctorToken, String> {
    boolean existsByDoctorIdAndDate(String doctorId, LocalDate date);
}
