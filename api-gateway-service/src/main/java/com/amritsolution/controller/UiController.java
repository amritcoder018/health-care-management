package com.amritsolution.controller;

import com.amritsolution.model.dto.ServiceRunningStatus;
import com.amritsolution.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/public")
public class UiController {
    @Autowired
    private GatewayService gatewayService;
    @GetMapping(value="/doctor/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getDoctorDTOs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return gatewayService.getAllDoctos(size,page);
    }

    @GetMapping("/wakeup")
    public Mono<ServiceRunningStatus> wakeup() {
        ServiceRunningStatus serviceStatusDto=new ServiceRunningStatus(1,"gateway-service");

        return Mono.just(serviceStatusDto);
    }
}
