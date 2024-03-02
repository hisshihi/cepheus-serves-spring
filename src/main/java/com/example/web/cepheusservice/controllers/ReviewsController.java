package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ReviewsDto;
import com.example.web.cepheusservice.domain.entity.ReviewsEntity;
import com.example.web.cepheusservice.mappers.impl.ReviewsMapperImpl;
import com.example.web.cepheusservice.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final ReviewsMapperImpl reviewsMapper;

    @PostMapping(path = "/reviews")
    private ResponseEntity<ReviewsDto> save(@RequestBody ReviewsDto reviewsDto) {
        ReviewsEntity reviewsEntity = reviewsMapper.mapFrom(reviewsDto);
        ReviewsEntity newReviewsEntity = reviewsService.save(reviewsEntity);
        return new ResponseEntity<>(reviewsMapper.mapTo(newReviewsEntity), HttpStatus.CREATED);
    }

}
