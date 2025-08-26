package com.amritsolution.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class JwtValidatorService {
    @Value("${spring.app.jwtSecret}")
    private String secret;

    Logger logger= LoggerFactory.getLogger(JwtValidatorService.class);

    public JwtValidatorService() {
    }

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization Header: {}", bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove Bearer prefix
        }
        return null;
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
    }

    public String extractUsername(Claims claims) {
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
    public Set<String> extractRoles(Claims claims) {
        List<?> k=claims.get("roles",List.class);

        if(k!=null)
        {
            return k.stream().map(Object::toString).collect(Collectors.toSet());
        }

        return Set.of(); // roles claim stored in token
    }

    public boolean validateToken(String token) {
        try {
            System.out.println("Validate");
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    protected SecretKey getSigningKey()
    {
        return Keys.hmacShaKeyFor(this.secret.getBytes());
    }

}