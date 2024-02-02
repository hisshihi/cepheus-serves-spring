package com.example.web.cepheusservice.config;

import com.example.web.cepheusservice.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
    private final UserDetailsService userDetailsService;

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
//            Если заголовок отсутствует или не начинается с Bearer, фильтр пропускает запрос.
            filterChain.doFilter(request, response);
            return;
        }
//        Если заголовок валидный, то из него извлекается сам JWT-токен.
//        Извлекаем токен и заголовка
        jwt = authHeader.substring(7);
        // todo извлекаем userEmail из JWT токена;
        userEmail = jwtService.extractUseremail(jwt);
//        Проверка JWT-токена:
//        Если email не null и пользователь не аутентифицирован, то происходит загрузка информации о пользователе из сервиса UserDetailsService
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            Из токена извлекается email пользователя.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            /*
            * Если токен и пользователь валдный
            * Тогда создаём новый объект и передаём ему данные пользователя, учётные данные и полномочия. Создание нового объекта аутентификации пользователя.
            * Затем дополняем токен деталями запроса
            * Затем обновляем токен аунтификации
            * */
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
//        Передаём следуюзему фильтру запрос и ответ
        filterChain.doFilter(request, response);
    }
}
