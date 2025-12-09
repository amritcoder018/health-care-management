package com.amritsolution.service;

import com.amritsolution.model.dto.LoginRequestDTO;
import com.amritsolution.model.dto.TokenResponse;
import com.amritsolution.utils.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResponseEntity<?> loginAsPatient(LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
        }catch (BadCredentialsException e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                                    .body(Map.of("error","Username or password not correct!","responseGeneratedAt",LocalDateTime.now().toString()));
        }


        String jwt=jwtUtil.generateToken(loginRequestDTO.getUsername());
        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);             // works on Render and does NOT break localhost
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setAttribute("SameSite", "None");  // required for GitHub Pages cross-site cookie
        // 4. Add cookie to response
        response.addCookie(cookie);

        return ResponseEntity.ok(TokenResponse.builder().token(jwt).generatedAt(LocalDateTime.now()).build());
    }

    @Override
    public ResponseEntity<?> validate(HttpServletRequest httpServletRequest) {

        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null) {
            return ResponseEntity.status(401).body("No cookies");
        }

        String token = null;

        for (Cookie c : cookies) {
            if ("token".equals(c.getName())) {
                token = c.getValue();
                break;
            }
        }

        if (token == null) {
            return ResponseEntity.status(401).body("Token cookie missing");
        }

        // Validate JWT
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("Token invalid or expired");
        }

        return ResponseEntity.ok("Valid");
    }

    @Override
    public ResponseEntity<TokenResponse> logoutAsStaff(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
