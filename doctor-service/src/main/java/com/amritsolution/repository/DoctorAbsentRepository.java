package com.amritsolution.repository;

import com.amritsolution.model.db.DoctorAbsentRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorAbsentRepository extends MongoRepository<DoctorAbsentRecord, String> {
    List<DoctorAbsentRecord> findAllByAbsentDate(LocalDate absentDate);
    boolean existsByDoctorIdAndAbsentDate(String doctorId, LocalDate absentDate);
}
