package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.Favorite;

import java.security.Principal;
import java.util.List;

public interface FavoriteService {
    Favorite save(Favorite favorite, Principal principal);

    List<Favorite> findAll(Principal principal);

    void delete(Long id);
}
