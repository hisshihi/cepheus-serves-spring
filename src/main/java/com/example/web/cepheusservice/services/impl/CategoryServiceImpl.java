package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.repositories.CategoryRepository;
import com.example.web.cepheusservice.services.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity findById(CategoryDto categoryDto) {
        return categoryRepository.findById(categoryDto.getId()).orElseThrow(() -> new RuntimeException("Категория не найдена"));
    }

}
