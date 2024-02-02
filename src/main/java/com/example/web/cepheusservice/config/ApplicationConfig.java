package com.example.web.cepheusservice.config;

import com.example.web.cepheusservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
//Этот код конфигурирует Spring Security для работы с базой данных пользователей.
public class ApplicationConfig {

    private final UserRepository userRepository;

//    Предоставляет информацию о пользователях для аутентификации.
//    В данном коде реализован с помощью метода userDetailsService(), который ищет пользователя по email в базе данных.
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

//    Проверяет подлинность пользователей.
//  В данном коде используется DaoAuthenticationProvider, который:
//  Использует UserDetailsService для получения информации о пользователе.
//  Сравнивает пароль, введенный пользователем, с паролем, хранящимся в базе данных.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        Пользователь
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        Закодирование пароля
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

//    Создаётся менеджер аунтификации
//    Отвечает за аутентификацию пользователей.
//  В данном коде создается с помощью authenticationConfiguration.getAuthenticationManager().
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
