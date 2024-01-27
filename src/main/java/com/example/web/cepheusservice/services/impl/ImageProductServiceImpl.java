package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.repositories.ImageProductRepository;
import com.example.web.cepheusservice.services.ImageProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.StreamSupport;

@Service
public class ImageProductServiceImpl implements ImageProductService {

    private ImageProductRepository imageProductRepository;

    public ImageProductServiceImpl(ImageProductRepository imageProductRepository) {
        this.imageProductRepository = imageProductRepository;
    }


    @Override
    public void save(MultipartFile multipartFile) throws IOException {

        ImageProductEntity imageProductEntity = new ImageProductEntity();
        imageProductEntity.setName(multipartFile.getOriginalFilename());
        imageProductEntity.setOriginalFileName(multipartFile.getOriginalFilename());
        imageProductEntity.setSize(multipartFile.getSize());
        imageProductEntity.setContentType(multipartFile.getContentType());
        imageProductEntity.setBytes(multipartFile.getBytes());
        imageProductRepository.save(imageProductEntity);
    }

}
