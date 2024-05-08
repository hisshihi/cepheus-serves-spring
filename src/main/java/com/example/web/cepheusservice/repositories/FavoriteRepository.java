package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.Favorite;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @EntityGraph(attributePaths = {"user", "product"})
    List<Favorite> findAllProductIdByUserId(Long id);

}
