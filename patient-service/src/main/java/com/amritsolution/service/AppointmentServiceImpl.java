package com.amritsolution.service;

import com.amritsolution.model.db.AppointmentDTO;
import com.amritsolution.model.db.OfflineAppointmentDTO;
import com.amritsolution.model.db.OnlineAppointmentDTO;
import com.amritsolution.repo.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private MicroserviceConnector microserviceConnector;
    @Autowired
    private PatientInfoRepository patientInfoRepository;


    @Override
    public ResponseEntity<?> bookAppointment(AppointmentDTO appointmentDTO) {
        if(appointmentDTO.isOnline()&& Objects.nonNull(appointmentDTO.getOnlineAppointment()))
        {
            String paymenturi="dfdfd";
            if (patientInfoRepository.findByPatientId(appointmentDTO.getPatientId()).get().isMember()) {
                Message<AppointmentDTO> message= MessageBuilder.withPayload(appointmentDTO)
                        .setHeader("topic","Patient_Appointment")
                        .setHeader("eventType","AppointmentRequest")
                        .setHeader("createdTime", LocalDateTime.now().toString())
                        .build();
                kafkaProducerService.sendMessageWithHeaders(message);
            }
            //microserviceConnector.establishConnection(HttpMethod.GET,)


        }
        return null;
    }

    @Override
    public void makeAnAppointmentOnline(AppointmentDTO appointmentDTO) {
        //check if payment is due
        //if not paid then throw exception
        //if paid:
        //publish on doctor kafka topic to get notified
        //create an kafka listener waiting for doctor approval
        //notify customer with email and update patient appointment in DB
    }

    @Override
    public void makeAnAppointmentOffline(AppointmentDTO appointmentDTO) {
        //check if payment is due
        //if not paid then throw exception
        //if paid then check from doctor what token no is available in a time slot.
        //then book the appointment and update db with apointment info
    }

    @Override
    public void cancelOnlineAppointment(AppointmentDTO appointmentDTO) {
        //check if apply for refund if applicable and within time period
        //update db appointment status and open window for booking;
    }

    @Override
    public void cancelOfflineAppointment(AppointmentDTO appointmentDTO) {
        //check if apply for refund if applicable and within time period
        //update db appointment status and open window for booking;
    }

    @Override
    public void cancelAppointmentById(String appointmentId) {
        //check if apply for refund if applicable and within time period
        //update db appointment status and open window for booking;
    }
}
