package com.example.web.cepheusservice.config;

import com.example.web.cepheusservice.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Используем фильмер для каждого запроса
// Обозначаем этот класс как компонент, чтобы им управлял spring
@Component
// Создаёт конструктор из любого поля, который будет объявлен в классе
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
//        Создаём строку, в которую передаём токен в заголовек для того, чтобы проверить, существует ли он
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
//        Првоеряем, существует ли токен, проверяем на null и начниается ли строка с Bearer и пробела
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            Вызываем цепочку фильров с запросом и ответом
            filterChain.doFilter(request, response);
            return;
        }
//        Извлекаем токен и заголовка
        jwt = authHeader.substring(7);
        // todo извлекаем userEmail из JWT токена;
        userEmail = jwtService.extractUseremail(jwt);
    }
}
