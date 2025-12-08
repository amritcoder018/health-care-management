package com.amritsolution.controller;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.service.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    AuthorizationService authorizationService;
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
}
