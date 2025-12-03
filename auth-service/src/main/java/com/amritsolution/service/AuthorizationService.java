package com.amritsolution.service;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {
    ResponseEntity<TokenResponse> loginAsPatient(LoginRequestDTO loginRequestDTO);
    ResponseEntity<TokenResponse> logoutAsStaff(LoginRequestDTO loginRequestDTO);
    //ResponseEntity<?> registerPatient()
}
