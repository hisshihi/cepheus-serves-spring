package com.example.web.cepheusservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private String city;
    private String buyer;
    private String deliveryMethod;
    private Long allCountProduct;
    private String allPrice;

}
