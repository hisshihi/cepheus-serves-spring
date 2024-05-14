package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.services.OrderEntityService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RequestMapping(path = "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderEntityService orderEntityService;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody OrderEntity order, Principal principal, UriComponentsBuilder ucb) {
        OrderEntity savedOrder = orderEntityService.saveOrder(order, principal);

        URI location = ucb.path("/order/{id}").buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
