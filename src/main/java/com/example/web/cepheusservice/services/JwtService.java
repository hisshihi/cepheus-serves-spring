package com.example.web.cepheusservice.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUseremail(String jwt);

    String generateToken(UserDetails userDetails);

    <T> T exctractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(Map<String, Object> extractClaims, UserDetails userDetails);

    boolean isTokenValid(String jwt, UserDetails userDetails);

    boolean isTokenExpired(String jwt);

    Date extractExpiration(String jwt);

    Claims extractAllClaims(String jwt);

    Key getSignInKey();

}
