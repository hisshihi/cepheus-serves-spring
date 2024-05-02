package com.example.web.cepheusservice.domain.entity;

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
@Table(name = "images")
public class ImageProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "imageProduct_id_seq")
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Lob
    private byte[] bytes;

}
