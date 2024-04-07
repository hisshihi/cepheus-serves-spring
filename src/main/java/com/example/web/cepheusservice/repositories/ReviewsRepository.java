package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<ReviewsEntity, Long> {

    List<ReviewsEntity> findByUserId(Long id);
}
