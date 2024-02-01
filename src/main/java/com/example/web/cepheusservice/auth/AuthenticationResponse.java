package com.example.web.cepheusservice.auth;

import com.example.web.cepheusservice.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

//    Создаём токен который будет отправлен обратно пользователю
    private String token;

}
