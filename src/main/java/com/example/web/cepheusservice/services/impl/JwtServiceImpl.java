package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

//    Этот класс реализует интерфейс JwtService и предоставляет методы для:
//
//  Извлечения email пользователя из JWT-токена.
//  Генерации JWT-токена для пользователя.
//  Проверки подлинности JWT-токена.
//  Извлечения различных claims из JWT-токена.

//    SECRET_KEY: Секретный ключ, используемый для подписи JWT-токенов.
    @Value("${web.cepheusservice.secret_key}")
    private final static String SECRET_KEY = "7a2357643121767939494e7a4036794c7276623c657936246e442b475a702068";

    //    extractUseremail(String jwt): Извлекает email пользователя из JWT-токена.
    @Override
    public String extractUseremail(String jwt) {
        return exctractClaim(jwt, Claims::getSubject);
    }

    // exctractClaim(String token, Function<Claims, T> claimsResolver): Извлекает claim из JWT-токена.
    public <T> T exctractClaim(String token, Function<Claims, T> claimsResolver) {
//        Извдекаем все поля из токена и возвращаем объект Claims
        final Claims claims = extractAllClaims(token);
        // Этот код позваляет извлечь любое значение из jwt токена
        return claimsResolver.apply(claims);
    }

    // generateToken(UserDetails userDetails): Генерирует JWT-токен для пользователя.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extractClaim = new HashMap<>();
        extractClaim.put("role", userDetails.getAuthorities().toString());
        return generateToken(extractClaim, userDetails);
    }

    // generateToken(Map<String, Object> extractClaims, UserDetails userDetails):
    // Генерирует JWT-токен для пользователя с дополнительными claims.
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 10 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

//    Проверка подлинности токена
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUseremail(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

//    Проверяет, истек ли срок действия JWT-токена.
    public boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date()); 
    }

//    Извлекает дату истечения срока действия из JWT-токена.
    public Date extractExpiration(String jwt) {
        return exctractClaim(jwt, Claims::getExpiration);
    }

    //    Извлекает все claims из JWT-токена.
    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) // Устанавливаем ключ подписи для проверки цифровой подписи подлинности
                .build()
                .parseClaimsJws(jwt) // Анализ токена
                .getBody(); // Получаем тело запроса


    }

//     Возвращает ключ подписи для JWT-токенов.
    public Key getSignInKey() {
//        Декодируем ключ
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
