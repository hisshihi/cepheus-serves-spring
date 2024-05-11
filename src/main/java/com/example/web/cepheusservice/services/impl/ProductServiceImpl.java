package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.ProductService;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Mapper<ProductEntity, ProductDto> mapper;

    public ProductServiceImpl(ProductRepository productRepository, Mapper<ProductEntity, ProductDto> mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) throws BadRequestException {
//        ProductImageEntity image = null;
//        if (image.getSize() != 0) {
//            image = toImageEntity(multipartFile);
//            productEntity.setProductImageEntity(image);
//        }

//        Boolean existTitle = productRepository.existsByTitle(productEntity.getTitle());
//        if (existTitle) {
//            throw new BadRequestException("Такой товар уже существует");
//        }

        return productRepository.save(productEntity);
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);
        return products.map(mapper::mapTo);
    }

    @Override
    public boolean isExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Optional<ProductEntity> findProduct(Long id) {
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
            Optional.ofNullable(productEntity.getImageProductEntity()).ifPresent(existingProduct::setImageProductEntity);
            Optional.ofNullable(productEntity.getSpecifications()).ifPresent(existingProduct::setSpecifications);
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new RuntimeException("Продукт не существует"));
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

    @Override
    public Page<ProductDto> findTop12ByOrderByCountDesc(Pageable pageable) {
//        return StreamSupport.stream(productRepository.findTop12ByOrderByCountDesc().spliterator(), false).collect(Collectors.toList());
        Page<ProductEntity> products = productRepository.findByOrderByCountDesc(pageable);
        return products.map(mapper::mapTo);

//        return productRepository.findByOrderByCountDesc(pageable);

    }

    @Override
    public Page<ProductEntity> filterByCategory(Long id, Pageable pageable) {
        return productRepository.findAllByCategoryEntityId(id, pageable);
    }

    @Override
    @Transactional
    public Page<ProductDto> findByPopular(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByOrderByCountDesc(pageable);
        return products.map(mapper::mapTo);
    }

    @Override
    @Transactional
    public Page<ProductDto> findAllByPriceAsc(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByOrderByPriceAsc(pageable);
        return products.map(mapper::mapTo);
    }

    @Override
    @Transactional
    public Page<ProductDto> findAllByPriceDesc(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByOrderByPriceDesc(pageable);
        return products.map(mapper::mapTo);
    }

    @Override
    @Transactional
    public Page<ProductDto> findByName(String name, Pageable pageable) {
        Page<ProductEntity> products = productRepository.findByTitleContainingIgnoreCase(name, pageable);
        return products.map(mapper::mapTo);
    }


}
