package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.Favorite;

import java.security.Principal;

public interface FavoriteService {
    Favorite save(Favorite favorite, Principal principal);
}
