package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>,
        PagingAndSortingRepository<ProductEntity, Long> {
}
