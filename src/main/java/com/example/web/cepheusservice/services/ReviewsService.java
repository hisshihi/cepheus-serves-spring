package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.ReviewsEntity;

import java.util.List;

public interface ReviewsService {
    ReviewsEntity save(ReviewsEntity reviewsEntity);

    List<ReviewsEntity> findAllReviews();

    List<ReviewsEntity> findREviewsByUserId(Long id);
}
