package com.amritsolution.controller;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.ServiceRunningStatus;
import com.amritsolution.service.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    WebClient webClient;

    @PostMapping("/patient")
    public ResponseEntity<?> loginAsPatient(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        log.info(loginRequestDTO.toString());
        return authorizationService.loginAsPatient(loginRequestDTO,response);
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validate(HttpServletRequest request) {
        log.info("validating");
        return authorizationService.validate(request);
    }
    @GetMapping("/wakeup")
    public Mono<ServiceRunningStatus> wakeup() {
        ServiceRunningStatus serviceStatusDto=new ServiceRunningStatus(1,"auth-service");

        return Mono.just(serviceStatusDto);
    }
}
