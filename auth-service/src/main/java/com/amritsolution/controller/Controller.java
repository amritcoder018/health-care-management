package com.amritsolution.controller;


import com.amritsolution.filter.AuthTokenFilter;
import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.LoginSuccessResponseDTO;
import com.amritsolution.model.dto.PatientSignUpDTO;
import com.amritsolution.service.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class Controller {
    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private JwtTokenService jwtTokenService;


    @GetMapping("/getuser")
    @PreAuthorize("hasRole('PATIENT')")
    public String getApi()
    {
        return "amrit";
    }
    @GetMapping("/getdoctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public String getApid()
    {
        return "doctor";
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        System.out.println("sss");
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
       logger.info("sssssssssssssk");
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        logger.info("1111111111111");
        SecurityContextHolder.getContext().setAuthentication(authentication);
logger.info("2222222222222");
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("3333333333333");

        String jwtToken = jwtTokenService.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        LoginSuccessResponseDTO response = new LoginSuccessResponseDTO(jwtToken,userDetails.getUsername(), roles );

        return ResponseEntity.ok(response);
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> registerPatient(@RequestBody PatientSignUpDTO patientSignUpDTO) {

        return ResponseEntity.ok("success");
    }
}

