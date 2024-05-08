package com.example.web.cepheusservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long productId;
    private Long count;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_link")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_link")
    private ProductEntity product;

}
