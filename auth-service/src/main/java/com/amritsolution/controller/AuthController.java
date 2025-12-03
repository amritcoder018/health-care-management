package com.amritsolution.controller;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.TokenResponse;
import com.amritsolution.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    AuthorizationService authorizationService;
    @PostMapping("/patient")
    public ResponseEntity<TokenResponse> loginAsPatient(@RequestBody LoginRequestDTO loginRequestDTO) {
        log.info(loginRequestDTO.toString());
        return authorizationService.loginAsPatient(loginRequestDTO);
    }
}
