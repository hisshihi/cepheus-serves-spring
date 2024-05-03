package com.example.web.cepheusservice.services;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SliderService {
    SliderEntity saveImage(MultipartFile multipartFile, String title, String text) throws IOException;

    List<SliderEntity> findAll();

    void delete(Long id);


    boolean isExists(Long id);

    void pathUpdate(Long id, SliderEntity sliderEntity);

    Optional<SliderEntity> findSliderById(Long id);
}
