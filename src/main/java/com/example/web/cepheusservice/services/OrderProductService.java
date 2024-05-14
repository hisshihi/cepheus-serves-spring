package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.OrderProduct;

import java.util.List;

public interface OrderProductService {
    List<OrderProduct> findAllById(Long id);
}
