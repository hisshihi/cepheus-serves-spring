package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.ReviewsDto;
import com.example.web.cepheusservice.domain.entity.ReviewsEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewsMapperImpl implements Mapper<ReviewsEntity, ReviewsDto> {

    private ModelMapper modelMapper;

    public ReviewsMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReviewsDto mapTo(ReviewsEntity reviewsEntity) {
        return modelMapper.map(reviewsEntity, ReviewsDto.class);
    }

    @Override
    public ReviewsEntity mapFrom(ReviewsDto reviewsDto) {
        return modelMapper.map(reviewsDto, ReviewsEntity.class);
    }
}
