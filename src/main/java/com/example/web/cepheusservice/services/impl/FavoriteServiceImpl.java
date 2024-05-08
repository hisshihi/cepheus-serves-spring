package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.Favorite;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.FavoriteRepository;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Favorite save(Favorite favorite, Principal principal) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(principal.getName());

        if (!userEntity.isPresent()) {
            throw new ExpressionException("Пользователь не найден");
        }

        Favorite savedFavorite = Favorite.builder()
                .userId(userEntity.get().getId())
                .productId(favorite.getProductId())
                .user(userRepository.findById(userEntity.get().getId()).orElseThrow(() -> new ExpressionException("Пользователь не найден")))
                .product(productRepository.findById(favorite.getProductId()).orElseThrow(() -> new ExpressionException("Товар не найден")))
                .build();
        return favoriteRepository.save(savedFavorite);
    }

}
