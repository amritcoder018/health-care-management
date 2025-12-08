package com.amritsolution.service;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {
    ResponseEntity<?> loginAsPatient(LoginRequestDTO loginRequestDTO, HttpServletResponse response);
    ResponseEntity<?> validate(HttpServletRequest httpServletRequest);
    ResponseEntity<TokenResponse> logoutAsStaff(LoginRequestDTO loginRequestDTO);
    //ResponseEntity<?> registerPatient()
}
