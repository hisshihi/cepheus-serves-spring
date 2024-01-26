package com.example.web.cepheusservice.domain.entity;

import com.example.web.cepheusservice.domain.dto.CategoryDto;
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
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

}
