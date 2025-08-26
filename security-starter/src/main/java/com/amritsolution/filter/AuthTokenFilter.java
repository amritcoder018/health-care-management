package com.amritsolution.filter;

import com.amritsolution.service.CustomUserDetails;
import com.amritsolution.service.JwtValidatorService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtValidatorService jwtTokenValidator;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("AuthTokenFilter called for URI: {}", request.getRequestURI());
        try {
            String jwt = jwtTokenValidator.getJwtFromHeader(request);
            logger.info("jwt string extracted : "+jwt);

            logger.info("Claims extracted");
            if (jwt != null && jwtTokenValidator.validateToken(jwt)) {
                logger.info("INside if condition");
                Claims claims=jwtTokenValidator.extractAllClaims(jwt);
                String username =jwtTokenValidator.extractUsername(claims);
                Set<String> roles=jwtTokenValidator.extractRoles(claims);
                logger.info("roles is {}",roles.toString());
                UserDetails userDetails = new CustomUserDetails(username,roles,null);
                logger.info("UserDetials object created");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                logger.info("authentication obj created");
                logger.info("Roles from JWT: {}", userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication:{}", e.getMessage());
        }
        logger.info("this class ended");
        filterChain.doFilter(request, response);
    }
}
