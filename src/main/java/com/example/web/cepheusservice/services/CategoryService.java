package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;

public interface CategoryService {
    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity findById(CategoryDto categoryDto);
}
