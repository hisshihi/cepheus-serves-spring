package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.mappers.impl.CategoryMapper;
import com.example.web.cepheusservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private CategoryService categoryService;
    private Mapper<CategoryEntity, CategoryDto> categoryMapper;

    public CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

//    Создание категорий
    @PostMapping(path = "/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity savedCategoryEntity = categoryService.save(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(savedCategoryEntity), HttpStatus.CREATED);
    }

//    Отображение всех категорий
    @GetMapping("/category")
    public List<CategoryDto> listCategory() {
        List<CategoryEntity> categories = categoryService.findALl();
        return categories.stream().map(categoryMapper::mapTo).collect(Collectors.toList());
    }
}
