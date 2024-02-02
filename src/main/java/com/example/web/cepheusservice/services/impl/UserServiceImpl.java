package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServise {

    private final UserRepository userRepository;

    @Override
    public UserEntity findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow();
    }

    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(findAll().spliterator(), false).collect(Collectors.toList());
    }
}
