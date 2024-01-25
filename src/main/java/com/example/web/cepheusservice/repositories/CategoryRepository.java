package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
