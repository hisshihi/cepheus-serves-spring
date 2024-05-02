package com.example.web.cepheusservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SliderDto {

    private Long id;
    private byte[] bytes;
    private String name;
    private String title;
    private String text;
    private String link;

}
