package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.ProductImageEntity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private String text;
    private Integer price;
    private CategoryDto categoryDto;

    private ProductImageDto productImageDto;
    //    Создаём новое поле, чтобы указывать изображение для превью
    private Long previewImageId;
    private LocalDateTime dateTime;

    @PrePersist
    private void init() {
        dateTime = LocalDateTime.now();
    }

}
