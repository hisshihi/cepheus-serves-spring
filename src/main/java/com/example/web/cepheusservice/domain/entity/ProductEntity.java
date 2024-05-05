package com.example.web.cepheusservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "product_id_seq")
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "text", nullable = false, columnDefinition = "VARCHAR(2100)")
    private String text;
    @Column(name = "price", nullable = false)
    private Integer price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;
//    При указывании типа ALl будет сохраняться не только изображения но и товар
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private ImageProductEntity imageProductEntity;
    @Lob
    private Long countSales;
    private Long count;
    @Column(columnDefinition = "VARCHAR(2000)")
    private String specifications;

//    Создаём новое поле, чтобы указывать изображение для превью
//    private Long previewImageId;

}
