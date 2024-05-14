package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import org.apache.coyote.BadRequestException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface OrderEntityService {
    OrderEntity saveOrder(OrderEntity order, Principal principal);

    List<OrderEntity> findOrderByUserId(Optional<UserEntity> findUser);
}
