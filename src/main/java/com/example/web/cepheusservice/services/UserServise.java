package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.UserEntity;

import java.util.List;

public interface UserServise {

    UserEntity findUserByEmail(String userEmail);

    List<UserEntity> findAll();
}
