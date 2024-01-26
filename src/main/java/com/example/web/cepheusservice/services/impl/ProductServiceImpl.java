package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public boolean isExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductEntity variableUpdate(Long id, ProductEntity productEntity) {
        productEntity.setId(id);

        return productRepository.findById(id).map(existingProduct -> {
            Optional.ofNullable(productEntity.getTitle()).ifPresent(existingProduct::setTitle);
            Optional.ofNullable(productEntity.getText()).ifPresent(existingProduct::setText);
            Optional.ofNullable(productEntity.getPrice()).ifPresent(existingProduct::setPrice);
            Optional.ofNullable(productEntity.getCategoryEntity()).ifPresent(existingProduct::setCategoryEntity);
            Optional.ofNullable(productEntity.getImg()).ifPresent(existingProduct::setImg);
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Продкут не существует"));
    }

    @Override
    public void delete(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Товар не найден"));
        productEntity.setCategoryEntity(null);
        productRepository.save(productEntity);

        productRepository.deleteById(id);
    }


}
