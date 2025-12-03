package com.amritsolution.repository;

import com.amritsolution.model.db.PatientUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<PatientUser, String> {
    Optional<PatientUser> findByUsername(String username);
}
