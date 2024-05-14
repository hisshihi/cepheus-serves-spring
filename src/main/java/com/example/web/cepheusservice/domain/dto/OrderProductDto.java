package com.example.web.cepheusservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDto {

    private Long id;

    private Long productEntityId;

    private int productCounts;
    private String productTitleImpl;

//    private OrderDto orderDto;

}
