package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.domain.entity.Statuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Statuses statuses;
    private List<OrderProductDto> orderProductDto;

}
