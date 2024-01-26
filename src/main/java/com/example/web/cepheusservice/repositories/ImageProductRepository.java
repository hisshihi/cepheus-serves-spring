package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.ProductImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends CrudRepository<ProductImageEntity, Long> {
}
