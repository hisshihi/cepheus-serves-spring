package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageProductService {

    ImageProductEntity save(MultipartFile multipartFile, Long id) throws IOException;



}
