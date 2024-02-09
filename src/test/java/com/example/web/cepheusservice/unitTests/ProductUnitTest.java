package com.example.web.cepheusservice.unitTests;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.controllers.ProductContoller;
import com.example.web.cepheusservice.domain.dto.ProductDto;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductContoller productContoller;

//    @Test
//    public void listHotProducts_success() {
//        ProductEntity productEntity = TestDataUtil.createProductEntity();
//        Pageable pageable = PageRequest.of(0, 12);
//        PageImpl<ProductEntity> products = new PageImpl<>(List.of(
//                new ProductEntity(1L, "Apple AirPods 2 Pro", "Apple AirPods 2 Pro", 19000, null, null, 2345L),
//                new ProductEntity(2L, "Apple AirPods 3 Pro", "Apple AirPods 3 Pro", 25000, null, null, 5432L)
//        ));
//        when(productService.findTop12ByOrderByCountDesc(pageable)).thenReturn(products);
//
//        Page<ProductDto> result = productContoller.listHotProducts(pageable);
//
//        assertThat(result.getContent()).hasSize(2);
//        assertThat(result.getContent().get(0).getTitle()).isEqualTo("Apple AirPods 2 Pro");
//        assertThat(result.getContent().get(1).getTitle()).isEqualTo("Apple AirPods 3 Pro");
//    }

}
