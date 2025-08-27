package com.amritsolution.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<OnlineAppointment,String> {

}
