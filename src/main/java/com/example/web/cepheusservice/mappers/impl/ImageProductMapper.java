package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.ImageProductDto;
import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImageProductMapper implements Mapper<ImageProductEntity, ImageProductDto> {

    private ModelMapper modelMapper;

    public ImageProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ImageProductDto mapTo(ImageProductEntity imageProductEntity) {
        return modelMapper.map(imageProductEntity, ImageProductDto.class);
    }

    @Override
    public ImageProductEntity mapFrom(ImageProductDto imageProductDto) {
        return modelMapper.map(imageProductDto, ImageProductEntity.class);
    }
}
