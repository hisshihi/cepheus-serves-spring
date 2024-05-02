package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.repositories.SliderEntityRepository;
import com.example.web.cepheusservice.services.SliderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

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

    @Override
    public List<SliderEntity> findAll() {
        return sliderEntityRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        sliderEntityRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return sliderEntityRepository.existsById(id);
    }


}
