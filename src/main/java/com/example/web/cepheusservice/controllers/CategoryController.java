package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.CategoryService;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    private Mapper<CategoryEntity, CategoryDto> categoryMapper;
    private ProductService productService;

    public CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper, ProductService productService) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.productService = productService;
    }

//    Создание категорий
    @PostMapping(path = "category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity savedCategoryEntity = categoryService.save(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(savedCategoryEntity), HttpStatus.CREATED);
    }

//    Отображение всех категорий
    @GetMapping("category")
    public List<CategoryDto> listCategory() {
        List<CategoryEntity> categories = categoryService.findALl();
        return categories.stream().map(categoryMapper::mapTo).collect(Collectors.toList());
    }

//    Поиск категории по id
    @GetMapping("category/{id}")
    private ResponseEntity<CategoryDto> findCategoryById(@PathVariable("id") Long id) {
        if (!categoryService.isExists(id)) {
            return ResponseEntity.notFound().build();
        }

        CategoryEntity categoryEntity = categoryService.findById(id);
        CategoryDto categoryDto = categoryMapper.mapTo(categoryEntity);
        return ResponseEntity.ok(categoryDto);
    }

//    Обновление категории
    @PutMapping(path = "category/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        categoryDto.setId(id);
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity updateCategoryEntity = categoryService.save(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(updateCategoryEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "category/{id}")
    public Object deleteCategopry(@PathVariable("id") Long id) {

        List<ProductEntity> productEntities = productService.findAllList();
        for (ProductEntity productEntity : productEntities) {
            if (productEntity.getCategoryEntity().getId() == id) {
                return "Нельзя удалить товар у которого есть эта категория";
            }
        }

        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
