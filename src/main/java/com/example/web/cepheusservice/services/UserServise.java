package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserServise {

    UserEntity findUserByEmail(String userEmail);

    List<UserEntity> findAll();

    boolean isExists(Long id);


    Optional<UserEntity> findUserById(Long id);

    void delete(Long id);

    UserEntity partialUpdate(Long id, UserEntity userEntity);

    void save(UserEntity user);
}
