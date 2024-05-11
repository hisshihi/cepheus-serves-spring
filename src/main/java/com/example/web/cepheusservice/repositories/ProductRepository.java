package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long>,
        PagingAndSortingRepository<ProductEntity, Long> {

    Page<ProductEntity> findByOrderByCountDesc(Pageable pageable);

//    @Query("select p from ProductEntity p where p.title = ?1")
    boolean existsByTitle(String title);

    Page<ProductEntity> findAllByCategoryEntityId(Long id, Pageable pageable);

    Page<ProductEntity> findByOrderByPriceAsc(Pageable pageable);

    Page<ProductEntity> findByOrderByPriceDesc(Pageable pageable);
}
