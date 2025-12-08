package com.amritsolution.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final String USER_SECRET = "your_super_secret_key_change_this_please_12345";
    private final String SERVICE_CLIENT_SECRET="your-256-bit-secret-key-must-be-long-1234567890";
    private final Key key = Keys.hmacShaKeyFor(USER_SECRET.getBytes());
    private final Key serviceClientKey= Keys.hmacShaKeyFor(SERVICE_CLIENT_SECRET.getBytes());

    public Claims getClaimsFromToken(String token) {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody();
    }
    public String extractUsername(String token) {
        return getClaimsFromToken(token)
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = getClaimsFromToken(token);

        // Extract roles from the "roles" claim (adjust name if needed)
        List<String> roles = claims.get("roles", List.class);

        if (roles == null) {
            return List.of();
        }

        return roles.stream()
                .map(role -> (GrantedAuthority) () -> role)
                .toList();
    }
    public String generateToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + 3600_000); // 1 hour

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(serviceClientKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
