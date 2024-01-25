package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity save(ProductEntity productEntity);


    Page<ProductEntity> findAll(Pageable pageable);

    boolean isExists(Long id);

    Optional<ProductEntity> findById(Long id);
}
