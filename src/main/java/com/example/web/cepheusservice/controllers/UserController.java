package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.auth.AuthenticationRequest;
import com.example.web.cepheusservice.auth.AuthenticationResponse;
import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.dto.UserDto;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.JwtService;
import com.example.web.cepheusservice.services.UserServise;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mapper<UserEntity, UserDto> mapper;
    private final UserServise userServise;
    private final JwtService jwtService;

    @PostMapping(path = "/users/{token}")
    public ResponseEntity<UserDto> getUsers(@PathVariable String token) {
        String userEmail = jwtService.extractUseremail(token);
        UserEntity userEntity = userServise.findUserByEmail(userEmail);
        UserDto userDto = mapper.mapTo(userEntity);

//        UserEntity userEntity = mapper.mapFrom(userDto);
//        System.out.println(userEntity);
        return ResponseEntity.ok(userDto);
    }

//    Получаения всех пользователей
    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> userEntityList = userServise.findAll();
        return userEntityList.stream().map(mapper::mapTo).collect(Collectors.toList());
    }

}
