package com.example.web.cepheusservice;

import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.Role;
import com.example.web.cepheusservice.domain.entity.UserEntity;

import java.time.LocalDateTime;

public class TestDataUtil {

    private TestDataUtil() {

    }

    public static CategoryEntity createTestCategoryA() {
        return CategoryEntity.builder()
                .id(1L)
                .title("Сервера")
                .build();
    }

    public static CategoryEntity createTestCategoryB() {
        return CategoryEntity.builder()
                .id(2L)
                .title("Наушники")
                .build();
    }

    public static CategoryEntity createTestCategoryC() {
        return CategoryEntity.builder()
                .id(3L)
                .title("Видеокарты")
                .build();
    }

    public static ProductEntity createProductEntity() {
        return ProductEntity.builder()
                .id(1L)
                .title("Apple AirPods 2 Pro")
                .text("Apple AirPods 2 Pro")
                .price(19000)
                .categoryEntity(createTestCategoryA())
                .imageProductEntity(null)
                .build();
    }

//    Пользователи
    public static UserEntity createUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .firstname("Hiss")
                .lastname("Test")
                .email("hisstest@gmail.com")
                .phone("123456789")
                .password("12345678")
                .role(Role.USER)
                .token("fdsadsf")
                .build();
    }

}
