package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.OrderDto;
import com.example.web.cepheusservice.domain.dto.OrderProductDto;
import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.mappers.impl.OrderMapper;
import com.example.web.cepheusservice.mappers.impl.OrderProductMapper;
import com.example.web.cepheusservice.services.OrderEntityService;
import com.example.web.cepheusservice.services.OrderProductService;
import com.example.web.cepheusservice.services.UserServise;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderEntityService orderEntityService;
    private final UserServise userServise;
    private final OrderProductService orderProductService;
    private final OrderProductMapper orderProductMapper;
    private final OrderMapper orderMapper;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody OrderEntity order, Principal principal, UriComponentsBuilder ucb) {
        OrderEntity savedOrder = orderEntityService.saveOrder(order, principal);

        URI location = ucb.path("/order/{id}").buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    private List<OrderDto> getOrderEntity(Principal principal) {
        UserEntity user = userServise.findUserByEmail(principal.getName());
        List<OrderEntity> orderEntities = orderEntityService.findOrderEntityByUserId(user.getId());
        return orderEntities.stream().map(orderMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/product/{id}")
    private List<OrderProductDto> getOrderProduct(@PathVariable Long id) {
        List<OrderProduct> orderProducts = orderProductService.findAllById(id);

        return orderProducts.stream().map(orderProductMapper::mapTo).collect(Collectors.toList());
    }

    @PatchMapping(path = "/{id}")
    private ResponseEntity<String> updateStatus(@PathVariable("id") Long id, @RequestBody OrderEntity order) {
        orderEntityService.update(id, order);
        return ResponseEntity.ok().build();
    }

}
