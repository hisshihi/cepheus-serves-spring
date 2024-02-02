package com.example.web.cepheusservice.mappers.impl;

import com.example.web.cepheusservice.domain.dto.UserDto;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<UserEntity, UserDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
