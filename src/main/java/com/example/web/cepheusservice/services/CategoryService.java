package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity save(CategoryEntity categoryEntity);

    CategoryEntity findById(CategoryDto categoryDto);


    List<CategoryEntity> findALl();

    void delete(Long id);
}
