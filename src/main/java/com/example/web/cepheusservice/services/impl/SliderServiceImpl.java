package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.repositories.SliderEntityRepository;
import com.example.web.cepheusservice.services.SliderService;
import jakarta.persistence.EntityNotFoundException;
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
    public SliderEntity saveImage(MultipartFile multipartFile, String title, String text, Long link) throws IOException {
        SliderEntity sliderEntity = new SliderEntity();
        sliderEntity.setSize(multipartFile.getSize());
        sliderEntity.setName(multipartFile.getName());
        sliderEntity.setBytes(multipartFile.getBytes());
        sliderEntity.setContentType(multipartFile.getContentType());
        sliderEntity.setOriginalFileName(multipartFile.getOriginalFilename());

        sliderEntity.setTitle(title);
        sliderEntity.setText(text);
        sliderEntity.setLink_id(link);
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

    @Override
    public void pathUpdate(Long id, SliderEntity sliderEntity) {
        sliderEntity.setId(id);
        SliderEntity existingSlider = sliderEntityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SliderEntity not found with id " + id));

        Optional.ofNullable(sliderEntity.getTitle()).ifPresent(existingSlider::setTitle);
        Optional.ofNullable(sliderEntity.getText()).ifPresent(existingSlider::setText);
        Optional.ofNullable(sliderEntity.getLink_id()).ifPresent(existingSlider::setLink_id);
        Optional.ofNullable(sliderEntity.getSize()).ifPresent(existingSlider::setSize);
        Optional.ofNullable(sliderEntity.getName()).ifPresent(existingSlider::setName);
        Optional.ofNullable(sliderEntity.getBytes()).ifPresent(existingSlider::setBytes);
        Optional.ofNullable(sliderEntity.getOriginalFileName()).ifPresent(existingSlider::setOriginalFileName);
        Optional.ofNullable(sliderEntity.getContentType()).ifPresent(existingSlider::setContentType);

        sliderEntityRepository.save(existingSlider);
    }

    @Override
    public Optional<SliderEntity> findSliderById(Long id) {
        return sliderEntityRepository.findById(id);
    }


}
