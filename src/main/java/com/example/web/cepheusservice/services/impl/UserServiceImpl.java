package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServise {

    private final UserRepository userRepository;

}
