package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    SliderEntity saveImage(MultipartFile multipartFile, String title, String text) throws IOException;
}
