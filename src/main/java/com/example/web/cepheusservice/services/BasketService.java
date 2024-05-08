package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.Basket;
import com.example.web.cepheusservice.domain.entity.UserEntity;

import java.security.Principal;
import java.util.List;

public interface BasketService {
    Basket save(Basket basket, Principal principal);

}
