package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.repositories.ImageProductRepository;
import com.example.web.cepheusservice.services.ImageProductService;
import org.springframework.stereotype.Service;

@Service
public class ImageProductServiceImpl implements ImageProductService {

    private ImageProductRepository productRepository;

    public ImageProductServiceImpl(ImageProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
