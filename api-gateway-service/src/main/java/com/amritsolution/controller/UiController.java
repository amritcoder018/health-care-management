package com.amritsolution.controller;

import com.amritsolution.service.GrpcClientService;
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
    private GrpcClientService grpcClientService;
    @GetMapping(value="/doctor/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDoctorDTOs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return grpcClientService.getAllDoctos(size,page);
    }
    @GetMapping("/wakeup")
    public Mono<String> wakeup() {
        return Mono
                .just("gateway-service up");
    }
}
