package com.amritsolution.service;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.TokenResponse;
import com.amritsolution.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<TokenResponse> loginAsPatient(LoginRequestDTO loginRequestDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String jwt=jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(TokenResponse.builder().token(jwt).generatedAt(LocalDateTime.now()).build());
    }

    @Override
    public ResponseEntity<TokenResponse> logoutAsStaff(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
