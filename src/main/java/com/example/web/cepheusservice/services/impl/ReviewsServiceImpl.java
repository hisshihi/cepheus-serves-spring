package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ReviewsEntity;
import com.example.web.cepheusservice.repositories.ReviewsRepository;
import com.example.web.cepheusservice.services.ReviewsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    @Override
    public ReviewsEntity save(ReviewsEntity reviewsEntity) {
        return reviewsRepository.save(reviewsEntity);
    }
}
