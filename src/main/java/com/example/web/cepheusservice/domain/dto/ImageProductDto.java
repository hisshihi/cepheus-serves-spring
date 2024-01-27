package com.example.web.cepheusservice.domain.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageProductDto {

    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;

    private byte[] bytes;

}
