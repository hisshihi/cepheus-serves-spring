package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.domain.entity.UserEntity;
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
import java.util.Optional;

@RequestMapping(path = "/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderEntityService orderEntityService;
    private final UserServise userServise;
    private final OrderProductService orderProductService;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody OrderEntity order, Principal principal, UriComponentsBuilder ucb) {
        OrderEntity savedOrder = orderEntityService.saveOrder(order, principal);

        URI location = ucb.path("/order/{id}").buildAndExpand(savedOrder.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getOrder(Principal principal) {
        UserEntity user = userServise.findUserByEmail(principal.getName());
        Optional<UserEntity> findUser = userServise.findUserById(user.getId());

        List<OrderEntity> orders = orderEntityService.findOrderByUserId(findUser);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "/product/{id}")
    private ResponseEntity<List<OrderProduct>> getOrderProduct(@PathVariable Long id) {
        List<OrderProduct> orderProducts = orderProductService.findAllById(id);
        return ResponseEntity.ok(orderProducts);
    }

}
