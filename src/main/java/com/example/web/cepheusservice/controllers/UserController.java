package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.dto.UserDto;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final Mapper<UserEntity, UserDto> mapper;
    private final UserServise userServise;

    @GetMapping(path = "/users")
    public ResponseEntity<UserDto> getUsers(@RequestBody UserDto userDto) {
        UserEntity userEntity = mapper.mapFrom(userDto);
        System.out.println(userEntity);
        return ResponseEntity.ok(mapper.mapTo(userEntity));
    }

}
