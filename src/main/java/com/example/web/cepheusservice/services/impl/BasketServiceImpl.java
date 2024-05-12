package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.Basket;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.BasketRepository;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Basket save(Basket basket, Principal principal) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(principal.getName());

        if (!userEntity.isPresent()) {
            throw new ExpressionException("Пользователь не найден");
        }

        Long userId = userEntity.get().getId();
        Long productId = basket.getProductId();

//        Пытаемся найти существующую запись в корзине
        Optional<Basket> existingBasket = basketRepository.findByUserIdAndProductId(userId, productId);

        if (existingBasket.isPresent()) {
            Basket updatedBasket = existingBasket.get();
            updatedBasket.setCount(updatedBasket.getCount() + 1);
            return basketRepository.save(updatedBasket);
        } else {
            Basket newBasket = Basket.builder()
                    .userId(userId)
                    .productId(productId)
                    .count(1L)
                    .user(userEntity.get())
                    .product(productRepository.findById(productId).orElseThrow(() -> new ExpressionException("Товар не найден")))
                    .build();

            return basketRepository.save(newBasket);
        }
    }

    @Override
    @Transactional
    public List<Basket> findProduct(Principal principal) {
        Optional<UserEntity> user = userRepository.findByEmail(principal.getName());

        return basketRepository.findByUserId(user.get().getId());
    }

    @Override
    public void delete(Long id) {
        basketRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void decreaseCount(Principal principal, Long basketId) {
        UserEntity userEntity = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new ExpressionException("Пользователь не найден"));

        Basket basket = basketRepository.findById(basketId)
                .orElseThrow(() -> new ExpressionException("Корзина не найдена"));

        // Проверяем, принадлежит ли корзина текущему пользователю
        if (!basket.getUserId().equals(userEntity.getId())) {
            throw new AccessDeniedException("Доступ запрещен");
        }

        if (basket.getCount() > 1) {
            basket.setCount(basket.getCount() - 1);
            basketRepository.save(basket);
        } else {
            basketRepository.deleteById(basketId);
        }
    }

}
