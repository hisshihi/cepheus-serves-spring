package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.OrderProductDto;
import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductMapper implements Mapper<OrderProduct, OrderProductDto> {

    private final ModelMapper modelMapper;

    @Override
    public OrderProductDto mapTo(OrderProduct orderProduct) {
        return modelMapper.map(orderProduct, OrderProductDto.class);
    }

    @Override
    public OrderProduct mapFrom(OrderProductDto orderProductDto) {
        return modelMapper.map(orderProductDto, OrderProduct.class);

    }
}
