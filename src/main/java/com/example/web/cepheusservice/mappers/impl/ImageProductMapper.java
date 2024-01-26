package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.ProductImageDto;
import com.example.web.cepheusservice.domain.entity.ProductImageEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageProductMapper implements Mapper<ProductImageEntity, ProductImageDto> {

    private ModelMapper modelMapper;

    public ImageProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductImageDto mapTo(ProductImageEntity productImageEntity) {
        return modelMapper.map(productImageEntity, ProductImageDto.class);
    }

    @Override
    public ProductImageEntity mapFrom(ProductImageDto productImageDto) {
        return modelMapper.map(productImageDto, ProductImageEntity.class);
    }
}
