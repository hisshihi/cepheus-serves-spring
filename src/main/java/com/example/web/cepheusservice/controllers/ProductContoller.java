package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.mappers.impl.CategoryMapper;
import com.example.web.cepheusservice.services.CategoryService;
import com.example.web.cepheusservice.services.ImageProductService;
import com.example.web.cepheusservice.services.ProductService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@MultipartConfig
public class ProductContoller {

    private Mapper<ProductEntity, ProductDto> productMapper;
    private ProductService productService;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private ImageProductService imageProductService;


    public ProductContoller(Mapper<ProductEntity, ProductDto> productMapper,
                            ProductService productService,
                            CategoryService categoryService,
                            ImageProductService imageProductService,
                            CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageProductService = imageProductService;
        this.categoryMapper = categoryMapper;
    }

    //    Создание нового товара
    @PostMapping(path = "products", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestParam("title") String title,
                                                    @RequestParam("text") String text,
                                                    @RequestParam("price") Integer price,
                                                    @ModelAttribute CategoryDto categoryDto,
                                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(title);
        productEntity.setText(text);
        productEntity.setPrice(price);

//        Заполняю count случайными числами при создании
        Random random = new Random();
        Long randNumber = random.nextLong(2000L);

        productEntity.setCount(randNumber);

        // Получаем экземпляр сущности категории по ID
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity foundCategory = categoryService.findById(categoryEntity);

        // Устанавливаем категорию для товара
        productEntity.setCategoryEntity(foundCategory);

        // Сохраняем товар в базу данных
        ProductEntity savedProductEntity = productService.save(productEntity);

        // Сохраняем изображение товара
        ImageProductEntity imageProductEntity = imageProductService.save(multipartFile, savedProductEntity.getId());

        // Устанавливаем изображение товара
        productEntity.setImageProductEntity(imageProductEntity);

        // Сохраняем товар с изображением в базу данных
        savedProductEntity = productService.save(productEntity);

        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.CREATED);
    }


    //    Отображение всех товаров
    @GetMapping(path = "/products")
    public Page<ProductDto> listProducts(Pageable pageable) {
        Page<ProductEntity> products = productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

//    Отображение самых популярных товаров
@GetMapping(path = "/products/hot")
@Transactional
public Page<ProductDto> listHotProducts(Pageable pageable) {
    // Ограничиваем количество товаров, возвращаемых сервисом
    PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 6);
    Page<ProductEntity> productsPage = productService.findTop12ByOrderByCountDesc(pageRequest);

    if (pageable.getPageNumber() > 1) return Page.empty();

    return productsPage.map(productMapper::mapTo);
}

    //    Поиск товара по id
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id) {
        Optional<ProductEntity> foundProduct = productService.findProduct(id);
        return foundProduct.map(productEntity -> {
            ProductDto foundProductDto = productMapper.mapTo(productEntity);
            return new ResponseEntity<>(foundProductDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //    Полное обновление товара

    @PutMapping(path = "products/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("text") String text,
                                                    @RequestParam("price") Integer price,
                                                    @ModelAttribute CategoryDto categoryDto,
                                                    @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Получаем товар из базы данных
        ProductEntity productEntity = productService.findProduct(id).orElseThrow(() -> new RuntimeException("Товар с таким id не найден"));

        // Обновляем поля товара
        productEntity.setTitle(title);
        productEntity.setText(text);
        productEntity.setPrice(price);

        // Получаем экземпляр сущности категории по ID
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity foundCategory = categoryService.findById(categoryEntity);

        // Устанавливаем категорию для товара
        productEntity.setCategoryEntity(foundCategory);

        // Сохраняем товар в базу данных
        ProductEntity savedProductEntity = productService.save(productEntity);

        if (savedProductEntity.getImageProductEntity() != null) {
            imageProductService.deleteImage(savedProductEntity.getImageProductEntity().getId());
        }

        // Сохраняем изображение товара
        ImageProductEntity imageProductEntity = imageProductService.save(multipartFile, savedProductEntity.getId());
        // Устанавливаем изображение товара
        productEntity.setImageProductEntity(imageProductEntity);

        // Сохраняем товар с изображением в базу данных
        savedProductEntity = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.OK);

    }


    //    Частичное обновление товара
    @PatchMapping(path = "products/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> variableUpdateProduct(@PathVariable(value = "id", required = false) Long id,
                                                            @RequestParam(value = "title", required = false) String title,
                                                            @RequestParam(value = "text", required = false) String text,
                                                            @RequestParam(value = "price", required = false) Integer price,
                                                            @ModelAttribute CategoryDto categoryDto,
                                                            @RequestParam(value = "image", required = false) MultipartFile multipartFile) {
        if (!productService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(title);
        productEntity.setText(text);
        productEntity.setPrice(price);

//        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
//        CategoryEntity findCategoryEntity = categoryService.findById(categoryEntity);
//
//        productEntity.setCategoryEntity(findCategoryEntity);

        ProductEntity savedProductEntity = productService.variableUpdate(id, productEntity);
        return new ResponseEntity<>(productMapper.mapTo(savedProductEntity), HttpStatus.OK);
    }

    //    Удаление товара
    @DeleteMapping(path = "products/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    Фильтр по категориям
    @GetMapping(path = "/porducts/filter/category/{id}")
    @Transactional
    public Page<ProductDto> filterByCategory(@PathVariable("id") Long id, Pageable pageable) {
        Page<ProductEntity> products = productService.filterByCategory(id, pageable);
        return new ResponseEntity<>(products.map(productMapper::mapTo), HttpStatus.OK).getBody();
    }

}
