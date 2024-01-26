package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.ProductImageEntity;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
//        ProductImageEntity image = null;
//        if (image.getSize() != 0) {
//            image = toImageEntity(multipartFile);
//            productEntity.setProductImageEntity(image);
//        }

        return productRepository.save(productEntity);
    }

    private ProductImageEntity toImageEntity(MultipartFile multipartFile) throws IOException {
        ProductImageEntity image = new ProductImageEntity();
        image.setName(multipartFile.getName());
        image.setSize(multipartFile.getSize());
        image.setOriginalFileName(multipartFile.getOriginalFilename());
        image.setBytes(multipartFile.getBytes());
        image.setContentType(multipartFile.getContentType());
        return image;
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

    @Override
    public List<ProductEntity> findAllList() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


}
