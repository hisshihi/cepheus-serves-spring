package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY = "7a2357643121767939494e7a4036794c7276623c657936246e442b475a702068";

    //    Получаем конкретного пользователя
    @Override
    public String extractUseremail(String jwt) {
        return exctractClaim(jwt, Claims::getSubject);
    }

    public <T> T exctractClaim(String token, Function<Claims, T> claimsResolver) {
//        Извдекаем все поля из токена и возвращаем объект Claims
        final Claims claims = extractAllClaims(token);
        // Этот код позваляет извлечь любое значение из jwt токена
        return claimsResolver.apply(claims);
    }

    // Генерация токена на основе данных пользователя
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Генерация токена для ползователя
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    Проверка подлинности токена
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUseremail(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

//    Првоерка не истёк ли срок годности токена
    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date()); 
    }

    public Date extractExpiration(String jwt) {
        return exctractClaim(jwt, Claims::getExpiration);
    }

    //    Извлечение всех параметров из токена
    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // Устанавливаем ключ подписи для проверки цифровой подписи подлинности
                .build()
                .parseClaimsJws(jwt) // Анализ токена
                .getBody(); // Получаем тело запроса


    }

    public Key getSignInKey() {
//        Декодируем ключ
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
