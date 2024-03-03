package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ReviewsDto;
import com.example.web.cepheusservice.domain.entity.ReviewsEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.exception.UserAlreadyExistsException;
import com.example.web.cepheusservice.mappers.impl.ReviewsMapperImpl;
import com.example.web.cepheusservice.services.ReviewsService;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final ReviewsMapperImpl reviewsMapper;
    private final UserServise userServise;

    @PostMapping("/reviews")
    private ResponseEntity<ReviewsDto> save(@RequestBody ReviewsDto reviewsDto) {
        Long userId = reviewsDto.getUser().getId();
        Optional<UserEntity> user = userServise.findUserById(userId);
        if (user.isPresent()) {
            reviewsDto.setUser(user.get());
            ReviewsEntity reviewsEntity = reviewsMapper.mapFrom(reviewsDto);
            reviewsEntity.setUser(user.get());
            ReviewsEntity newReviewsEntity = reviewsService.save(reviewsEntity);
            return new ResponseEntity<>(reviewsMapper.mapTo(newReviewsEntity), HttpStatus.CREATED);
        } else {
            throw new UserAlreadyExistsException("User not found with id: " + userId);
        }
    }

    @GetMapping("/reviews")
    private List<ReviewsDto> getAllReviews() {
        List<ReviewsEntity> reviewsEntities = reviewsService.findAllReviews();
        return reviewsEntities.stream().map(reviewsMapper::mapTo).collect(Collectors.toList());
    }

}
