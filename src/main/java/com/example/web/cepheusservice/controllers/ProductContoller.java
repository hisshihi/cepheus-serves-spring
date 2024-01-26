package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.CategoryService;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
    @PostMapping(path = "products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws IOException {
        System.out.println(productDto);
        ProductEntity productEntity = productMapper.mapFrom(productDto);

//        Находим категорию по id и устанавливаем её для товара
        CategoryEntity categoryEntity = categoryService.findById(productDto.getCategoryDto());
        productEntity.setCategoryEntity(categoryEntity);


        ProductEntity savedProductEntity = productService.save(productEntity);

//        Сохраняем файл
//        String fileName = multipartFile.getOriginalFilename();
//        Path filePath = Paths.get("uploads", fileName);
//        multipartFile.transferTo(filePath);

        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }

    //    Отображение всех товаров
    @GetMapping(path = "products")
    public Page<ProductDto> listProducts(Pageable pageable) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

    //    Поиск товара по id
    @GetMapping(path = "products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> foundProduct = productService.findById(id);
        return foundProduct.map(productEntity -> {
            ProductDto foundProductDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(foundProductDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //    Полное обновление товара
    @PutMapping(path = "products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDto.setId(id);
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity savedProductEntity = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.OK);

    }

    //    Частичное обновление товара
    @PatchMapping(path = "products/{id}")
    public ResponseEntity<ProductDto> variableUpdateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity savedProductEntity = productService.variableUpdate(id, productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.OK);
    }

    //    Удаление товара
    @DeleteMapping(path = "products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {


        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
