package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.repositories.OrderProductRepository;
import com.example.web.cepheusservice.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Override
    @Transactional
    public List<OrderProduct> findAllById(Long id) {
        return orderProductRepository.findByOrderId(id);
    }
}
