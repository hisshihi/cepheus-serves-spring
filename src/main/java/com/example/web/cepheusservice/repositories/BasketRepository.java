package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "baskets")
@CrossOrigin(origins = "http://localhost:8081", methods = {RequestMethod.POST, RequestMethod.GET})
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);

}
