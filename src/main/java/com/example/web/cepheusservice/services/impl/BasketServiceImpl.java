package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.Basket;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.BasketRepository;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public Basket save(Basket basket, Principal principal) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(principal.getName());



        Basket savedBasket = Basket.builder()
                .userId(userEntity.get().getId())
                .productId(basket.getProductId())
                .count(1L)
                .user(userRepository.findById(userEntity.get().getId()).orElseThrow(() -> new ExpressionException("Пользователь не найден")))
                .product(productRepository.findById(basket.getProductId()).orElseThrow(() -> new ExpressionException("Товар не найден")))
                .build();
        return basketRepository.save(savedBasket);
    }

}
