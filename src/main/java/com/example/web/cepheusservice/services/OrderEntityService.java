package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import org.apache.coyote.BadRequestException;

import java.security.Principal;

public interface OrderEntityService {
    OrderEntity saveOrder(OrderEntity order, Principal principal);
}
