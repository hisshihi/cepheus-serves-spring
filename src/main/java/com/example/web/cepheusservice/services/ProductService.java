package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.apache.coyote.BadRequestException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity save(ProductEntity productEntity) throws BadRequestException;

    @Cacheable(value = "products", key = "#pageable")
    Page<ProductDto> findAll(Pageable pageable);

    boolean isExists(Long id);

    Optional<ProductEntity> findProduct(Long id);

    ProductEntity variableUpdate(Long id, ProductEntity productEntity);

    void delete(Long id);

    List<ProductEntity> findAllList();


    Page<ProductEntity> findTop12ByOrderByCountDesc(Pageable pageable);

    Page<ProductEntity> filterByCategory(Long id, Pageable pageable);
}
