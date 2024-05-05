package com.example.web.cepheusservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long countSales;
    private Long count;
    private String specifications;
}
