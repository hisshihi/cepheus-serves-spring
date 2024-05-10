package com.example.web.cepheusservice.domain.entity;

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
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false, name = "user_link")
    private UserEntity user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false, name = "product_link")
    private ProductEntity product;

}
