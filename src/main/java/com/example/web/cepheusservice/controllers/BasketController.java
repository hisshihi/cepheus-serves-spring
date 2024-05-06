package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.Basket;
import com.example.web.cepheusservice.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("/basket")
    private ResponseEntity<String> saveAll(@RequestBody Basket basket, UriComponentsBuilder ucb, Principal principal) {

        Basket savedBasket = basketService.save(basket, principal);

        URI location = ucb.path("/basket/{id}").buildAndExpand(savedBasket.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
