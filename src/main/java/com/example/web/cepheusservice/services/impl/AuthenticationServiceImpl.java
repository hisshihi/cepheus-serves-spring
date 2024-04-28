package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.auth.AuthenticationRequest;
import com.example.web.cepheusservice.auth.AuthenticationResponse;
import com.example.web.cepheusservice.auth.RegisterRequest;
import com.example.web.cepheusservice.domain.entity.Role;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.exception.UserAlreadyExistsException;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.AuthenticationService;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

//    Этот класс реализует методы для регистрации и аутентификации пользователей.
//Использует Spring Security для аутентификации пользователей.
//Использует JWT для создания токенов доступа для пользователей.

    //    userRepository: Репозиторий для работы с пользователями.
    private final UserRepository userRepository;
    //    passwordEncoder: Кодировщик паролей.
    private final PasswordEncoder passwordEncoder;
    //    jwtService: Сервис для создания JWT-токенов.
    private final JwtServiceImpl jwtService;
    //    authenticationManager: Менеджер аутентификации Spring Security.
    private final AuthenticationManager authenticationManager;

    private final UserServise userServise;

    @Override
//    Регистрирует нового пользователя.
    public AuthenticationResponse register(RegisterRequest request) {
        //        Создает новый объект UserEntity из данных запроса.
        //Кодирует пароль пользователя.
        //Сохраняет пользователя в базу данных.
        //Генерирует JWT-токен для пользователя.
        //Возвращает ответ с токеном.
        var user = UserEntity
                .builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .inOrganization(request.isInOrganization())
                .orgName(request.getOrgName())
                .orgAddress(request.getOrgAddress())
                .orgINN(request.getOrgINN())
                .role(Role.USER)
                .build();

        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        userRepository.save(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
//    Аутентифицирует пользователя.
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        Проверяет учетные данные пользователя с помощью менеджера аутентификации.
//Ищет пользователя в базе данных по его email.
//Генерирует JWT-токен для пользователя.
//Возвращает ответ с токеном.
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        user.setToken(jwtToken);
        userRepository.save(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
