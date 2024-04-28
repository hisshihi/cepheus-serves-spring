package com.example.web.cepheusservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    JwtAuthenticationFilter jwtAuthenticationFilter:
//    Это поле содержит экземпляр класса JwtAuthenticationFilter, который отвечает за обработку аутентификации на основе JWT.
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    AuthenticationProvider authenticationProvider:
//    Это поле содержит экземпляр класса AuthenticationProvider, который отвечает за проверку учетных данных пользователя.
    private final AuthenticationProvider authenticationProvider;

    @Bean
//    securityFilterChain(HttpSecurity httpSecurity):
//    Этот метод отвечает за конфигурацию цепочки фильтров безопасности.
//    Цепочка фильтров безопасности - это серия фильтров, которые вызываются для обработки HTTP-запросов и ответов.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/register", "/authenticate", "/products") // Главная страница, страница авторизации и регистрации
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/products/{id}")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/reviews")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}

//.csrf().disable(): Эта строка отключает CSRF-защиту (Cross-Site Request Forgery).
// CSRF - это тип уязвимости веб-безопасности, который может быть использован,
// чтобы обмануть пользователя и заставить его неосознанно выполнить действие в веб-приложении.

//.authorizeRequests() : Эта строка конфигурирует правила авторизации для приложения.
//.antMatchers("/", "/register", "/authenticate"): Эта строка разрешает доступ к корневому пути, странице регистрации и странице аутентификации без аутентификации.
//.anyRequest(): Эта строка сопоставляет все остальные запросы.
//.authenticated(): Эта строка требует, чтобы пользователь был аутентифицирован для всех остальных запросов.

//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS):
// Эта строка конфигурирует управление сеансами.
// Она устанавливает политику создания сеансов как STATELESS,
// что означает, что приложение не будет создавать сеансы.

//.authenticationProvider(authenticationProvider):
// Эта строка добавляет authenticationProvider в список поставщиков аутентификации.
// Поставщик аутентификации отвечает за проверку учетных данных пользователя.

//.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class):
// Эта строка добавляет jwtAuthenticationFilter в цепочку фильтров перед UsernamePasswordAuthenticationFilter.
// UsernamePasswordAuthenticationFilter отвечает за обработку аутентификации на основе имени пользователя/пароля.

//.requestMatchers("/admin/**") // Админ панель
//                .hasRole("ADMIN")