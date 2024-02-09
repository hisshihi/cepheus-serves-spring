package com.example.web.cepheusservice.domain.dto;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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

    private ImageProductDto imageProductDto;

    private Long count;
}
