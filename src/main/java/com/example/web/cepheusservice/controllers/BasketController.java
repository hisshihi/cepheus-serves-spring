package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.Basket;
import com.example.web.cepheusservice.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

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

    //    Метод определения есть ли товар в коризне или нет
    @GetMapping("/basket/in-basket")
    private ResponseEntity<List<Basket>> getProductInBasket(Principal principal) {
        List<Basket> baskets = basketService.findProduct(principal);
        return ResponseEntity.ok(baskets);
    }

    @DeleteMapping("/basket/{id}")
    private ResponseEntity<String> deleteBasket(@PathVariable Long id) {
        basketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/basket/decrease/{id}")
    public ResponseEntity<String> decreaseProductCount(@PathVariable Long id, Principal principal) {
        basketService.decreaseCount(principal, id);
        return ResponseEntity.ok().build();
    }

}
