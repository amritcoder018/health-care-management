package com.amritsolution.controller;

import com.amritsolution.model.dto.LoginRequestDTO;
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
    public Flux<String> aggregateResponses() {
        // URLs for the three downstream services
        String gateway_service = "https://health-care-management-gateway-service.onrender.com/public/wakeup";
        String doctor_service = "https://health-care-management-doctor-service.onrender.com/public/wakeup";

        // Create a Flux of Monos, each representing a call to one service
        Flux<String> combinedFlux = Flux.merge(
                webClient.get().uri(gateway_service)
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofMinutes(3)),
                        //.onErrorResume(e -> Mono.just(" gateway-service timed out")),
                webClient.get().uri(doctor_service)
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofMinutes(3))
                       // .onErrorResume(e -> Mono.just("doctor-service timed out"))
        );

        // Return the combined flux of responses
        return combinedFlux;
    }
}
