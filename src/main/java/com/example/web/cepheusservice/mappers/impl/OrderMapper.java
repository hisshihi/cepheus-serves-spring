package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.OrderDto;
import com.example.web.cepheusservice.domain.dto.OrderProductDto;
import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper implements Mapper<OrderEntity, OrderDto> {

    private final ModelMapper modelMapper;
    private final OrderProductMapper orderProductMapper;

    @Override
    public OrderDto mapTo(OrderEntity order) {
        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        List<OrderProductDto> orderProductDtoList = order.getOrderProducts().stream()
                .map(orderProductMapper::mapTo)
                .collect(Collectors.toList());
        orderDto.setOrderProductDto(orderProductDtoList);
        return orderDto;
    }

    @Override
    public OrderEntity mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }
}
