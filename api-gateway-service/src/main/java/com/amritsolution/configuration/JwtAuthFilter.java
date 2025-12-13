package com.amritsolution.configuration;

import com.amritsolution.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;

        String path = request.getRequestURI();

        // allow authentication endpoints without JWT
        if (path.startsWith("/public/wakeup")) {
            log.info("wakeup intercepted");
            filterChain.doFilter(request, response);
            return;
        }


        if (token==null && (authHeader == null || !authHeader.startsWith("Bearer "))) {
            log.info("Authorization header not present in request");
            writeError(response, "No access token provided");
            return;
        }
        else if(token==null){
            token = authHeader.substring(7);
        }
        if (!jwtUtil.validateToken(token)) {
            log.info("Invalid token");
            writeError(response, "Token validation failed");
            return;
        }
        String username = jwtUtil.extractUsername(token);

        if (username == null) {
            log.info("Username not present in request");
            writeError(response, "Invalid token");
            return;
        }
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        jwtUtil.getAuthoritiesFromToken(token)
                );
log.info("Authentication Success");
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }
    private void writeError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"" + message + "\"}");
    }



}
