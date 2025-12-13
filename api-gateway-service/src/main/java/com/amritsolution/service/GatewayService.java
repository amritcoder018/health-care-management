package com.amritsolution.service;

import com.amritsolution.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GatewayService {

    @Autowired
    private JwtUtil jwtService;
@Autowired
    WebClient webClient;
    public Mono<String> getAllDoctos(int pageSize, int pageNumber) {
        String token = jwtService.generateToken("gateway-service");
        return webClient.get()
                .uri("https://health-care-management-doctor-service.onrender.com/public/getAll")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve().bodyToMono(String.class);
    }
}
