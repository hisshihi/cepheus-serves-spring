package com.example.web.cepheusservice.controllers;

import com.example.web.cepheusservice.domain.dto.ImageProductDto;
import com.example.web.cepheusservice.domain.entity.ImageProductEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import com.example.web.cepheusservice.services.ImageProductService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@MultipartConfig
public class ImageProductController {

    private ImageProductService imageProductService;
    private Mapper<ImageProductEntity, ImageProductDto> imageProductMapper;

    public ImageProductController(ImageProductService imageProductService, Mapper<ImageProductEntity, ImageProductDto> imageProductMapper) {
        this.imageProductService = imageProductService;
        this.imageProductMapper = imageProductMapper;
    }

    @PostMapping(path = "image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ImageProductEntity> createImageProduct(@RequestParam("image") MultipartFile multipartFile) throws IOException {
//        ImageProductEntity iamgeProductEntity = imageProductMapper.mapFrom(imageProductDto);
        imageProductService.save(multipartFile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
