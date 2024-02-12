package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.ProductService;
import org.apache.coyote.BadRequestException;
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

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
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

    @Override
    public Page<ProductEntity> findTop12ByOrderByCountDesc(Pageable pageable) {
//        return StreamSupport.stream(productRepository.findTop12ByOrderByCountDesc().spliterator(), false).collect(Collectors.toList());

        return productRepository.findByOrderByCountDesc(pageable);

    }


}
