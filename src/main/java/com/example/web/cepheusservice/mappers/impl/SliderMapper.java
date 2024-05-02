package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.SliderDto;
import com.example.web.cepheusservice.domain.entity.SliderEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SliderMapper implements Mapper<SliderEntity, SliderDto> {

    private final ModelMapper mapper;

    @Override
    public SliderDto mapTo(SliderEntity sliderEntity) {
        return mapper.map(sliderEntity, SliderDto.class);
    }

    @Override
    public SliderEntity mapFrom(SliderDto sliderDto) {
        return mapper.map(sliderDto, SliderEntity.class);
    }
}
