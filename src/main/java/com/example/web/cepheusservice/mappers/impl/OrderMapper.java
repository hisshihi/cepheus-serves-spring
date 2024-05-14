package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.OrderDto;
import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<OrderEntity, OrderDto> {

    private final ModelMapper modelMapper;

    @Override
    public OrderDto mapTo(OrderEntity order) {
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderEntity mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }
}
