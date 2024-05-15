package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "baskets")
@CrossOrigin(origins = "https://cepheus-service.netlify.app", methods = {RequestMethod.POST, RequestMethod.GET})
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUserIdAndProductId(Long userId, Long productId);

    List<Basket> findByUserId(Long userId);

}
