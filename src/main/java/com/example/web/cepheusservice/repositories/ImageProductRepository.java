package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImageProductRepository extends CrudRepository<ImageProductEntity, Long> {

    ImageProductEntity findByProductEntity(ProductEntity productEntity);

}
