package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.repositories.SliderEntityRepository;
import com.example.web.cepheusservice.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final SliderEntityRepository sliderEntityRepository;

    @Override
    public SliderEntity saveImage(MultipartFile multipartFile, String title, String text) throws IOException {
        SliderEntity sliderEntity = new SliderEntity();
        sliderEntity.setSize(multipartFile.getSize());
        sliderEntity.setName(multipartFile.getName());
        sliderEntity.setBytes(multipartFile.getBytes());
        sliderEntity.setContentType(multipartFile.getContentType());
        sliderEntity.setOriginalFileName(multipartFile.getOriginalFilename());

        sliderEntity.setTitle(title);
        sliderEntity.setText(text);
        sliderEntityRepository.save(sliderEntity);
        return sliderEntity;
    }
}
