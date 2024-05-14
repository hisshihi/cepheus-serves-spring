package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.repositories.OrderProductRepository;
import com.example.web.cepheusservice.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

}
