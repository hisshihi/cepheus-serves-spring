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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
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
    private ResponseEntity<String> save(@RequestBody ReviewsDto reviewsDto, Principal principal, UriComponentsBuilder ucb) {
        String userEmail = principal.getName();
        UserEntity user = userServise.findUserByEmail(userEmail);
        if (user != null) {
            reviewsDto.setUser(user);
            ReviewsEntity reviewsEntity = reviewsMapper.mapFrom(reviewsDto);
            reviewsEntity.setUser(user);
            ReviewsEntity newReviewsEntity = reviewsService.save(reviewsEntity);
            URI locationOfNewRewiew = ucb.path("/reviews/{id}").buildAndExpand(newReviewsEntity.getId()).toUri();
            return ResponseEntity.created(locationOfNewRewiew).build();
        } else {
            throw new UserAlreadyExistsException("User not found with id: " + userEmail);
        }
    }

    @GetMapping("/reviews")
    private List<ReviewsDto> getAllReviews() {
        List<ReviewsEntity> reviewsEntities = reviewsService.findAllReviews();
        return reviewsEntities.stream().map(reviewsMapper::mapTo).collect(Collectors.toList());
    }

}
