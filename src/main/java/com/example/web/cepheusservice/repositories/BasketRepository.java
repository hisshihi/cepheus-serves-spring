package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.Basket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @EntityGraph(attributePaths = {"user", "product"})
    List<Basket> findAllProductIdByUserId(Long id);

}
