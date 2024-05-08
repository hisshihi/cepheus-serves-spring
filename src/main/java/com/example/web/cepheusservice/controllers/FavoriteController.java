package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.entity.Favorite;
import com.example.web.cepheusservice.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RequestMapping(path = "favorite")
@RestController
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    private ResponseEntity<String> save(@RequestBody Favorite favorite, Principal principal, UriComponentsBuilder ucb) {
        Favorite savedFavorite = favoriteService.save(favorite, principal);

        URI location = ucb.path("/favorite/{id}").buildAndExpand(savedFavorite.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
