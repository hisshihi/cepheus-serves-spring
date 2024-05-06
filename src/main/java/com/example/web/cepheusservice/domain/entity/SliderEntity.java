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
public class SliderEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    private String title;
    @Column(columnDefinition = "VARCHAR(2000)")
    private String text;

    private Long link_id;

}
