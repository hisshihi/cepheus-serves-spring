package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.ImageProductRepository;
import com.example.web.cepheusservice.services.ImageProductService;
import com.example.web.cepheusservice.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageProductServiceImpl implements ImageProductService {

    private ImageProductRepository imageProductRepository;
    private ProductService productService;

    public ImageProductServiceImpl(ImageProductRepository imageProductRepository, ProductService productService) {
        this.imageProductRepository = imageProductRepository;
        this.productService = productService;
    }


    @Override
    public ImageProductEntity save(MultipartFile multipartFile, Long id) throws IOException {

        ImageProductEntity imageProductEntity = new ImageProductEntity();
        imageProductEntity.setName(multipartFile.getOriginalFilename());
        imageProductEntity.setOriginalFileName(multipartFile.getOriginalFilename());
        imageProductEntity.setSize(multipartFile.getSize());
        imageProductEntity.setContentType(multipartFile.getContentType());
        imageProductEntity.setBytes(multipartFile.getBytes());

//        Устанавливаем id и сохраняем товар к изображению
        ProductEntity productEntity = productService.findProduct(id).orElseThrow(() -> new RuntimeException("Товар с таким id не найден"));

        imageProductEntity.setProductEntity(productEntity);
        imageProductRepository.save(imageProductEntity);
        return imageProductEntity;
    }

    @Override
    public void deleteImage(Long id) {
        imageProductRepository.deleteById(id);
    }

    @Override
    public ImageProductEntity updateImage(MultipartFile multipartFile, Long id) throws IOException {
        Optional<ProductEntity> productEntity = productService.findProduct(id);
        ImageProductEntity imageProductEntity = imageProductRepository.findByProductEntity(productEntity.get());
        imageProductEntity.setBytes(multipartFile.getBytes());
        imageProductEntity.setSize(multipartFile.getSize());
        imageProductEntity.setName(multipartFile.getName());
        imageProductEntity.setContentType(multipartFile.getContentType());
        imageProductEntity.setOriginalFileName(multipartFile.getOriginalFilename());
        return imageProductRepository.save(imageProductEntity);
    }


}
