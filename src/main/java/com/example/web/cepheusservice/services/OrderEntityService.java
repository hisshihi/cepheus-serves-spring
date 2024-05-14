package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import org.apache.coyote.BadRequestException;

import java.security.Principal;
import java.util.List;

public interface OrderEntityService {
    OrderEntity saveOrder(OrderEntity order, Principal principal);

    List<OrderEntity> findOrderEntityByUserId(Long id);

    void update(Long id, OrderEntity order);

    List<OrderEntity> findALl();
}
