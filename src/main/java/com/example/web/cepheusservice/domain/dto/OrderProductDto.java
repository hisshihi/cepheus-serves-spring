package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

//    private OrderDto orderDto;

}
