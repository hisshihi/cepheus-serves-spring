package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.mappers.impl.ProductMapper;
import com.example.web.cepheusservice.repositories.CategoryRepository;
import com.example.web.cepheusservice.services.CategoryService;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ProductContoller {

    private Mapper<ProductEntity, ProductDto> productMapper;
    private ProductService productService;
    private CategoryService categoryService;


    public ProductContoller(Mapper<ProductEntity, ProductDto> productMapper, ProductService productService, CategoryService categoryService) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    //    Создание нового товара
    @PostMapping(path = "/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductEntity productEntity = productMapper.mapFrom(productDto);

//        Находим категорию по id и устанавливаем её для продукта
        CategoryEntity categoryEntity = categoryService.findById(productDto.getCategoryDto());
        productEntity.setCategoryEntity(categoryEntity);

        ProductEntity savedProductEntity = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }

//    Отображение всех товаров
    @GetMapping(path = "/products")
    public Page<ProductDto> listProducts(Pageable pageable) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

//    Поиск товара по id
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);
        return foundProduct.map(productEntity -> {
            ProductDto foundProductDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(foundProductDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    Полное обновление товара
    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDto.setId(id);
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity savedProductEntity = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.OK);

    }

}
