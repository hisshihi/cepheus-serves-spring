package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}
