package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.services.OrderEntityService;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RequestMapping(path = "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderEntityService orderEntityService;
    private final UserServise userServise;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody OrderEntity order, Principal principal, UriComponentsBuilder ucb) {
        OrderEntity savedOrder = orderEntityService.saveOrder(order, principal);

        URI location = ucb.path("/order/{id}").buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    private ResponseEntity<List<OrderEntity>> getOrderEntity(Principal principal) {
        UserEntity user = userServise.findUserByEmail(principal.getName());
        List<OrderEntity> orderEntities = orderEntityService.findOrderEntityByUserId(user.getId());
        return ResponseEntity.ok(orderEntities);
    }

}
