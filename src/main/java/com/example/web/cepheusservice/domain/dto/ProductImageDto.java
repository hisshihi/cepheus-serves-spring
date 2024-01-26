package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDto {

    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;

    private byte[] bytes;

    private ProductDto productDto;

}
