package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.Favorite;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "favorites")
@CrossOrigin(origins = "https://cepheus-service.netlify.app", methods = {RequestMethod.POST, RequestMethod.GET})
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByUserIdAndProductId(Long userId, Long productId);

    List<Favorite> findByUserId(Long userId);

}
