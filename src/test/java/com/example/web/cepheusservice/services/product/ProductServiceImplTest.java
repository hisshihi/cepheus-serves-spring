package com.example.web.cepheusservice.services.product;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.impl.ProductServiceImpl;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    private ProductServiceImpl underTest;

//    Перед каждым тестом создаём новый экземпляр ProductServiceImpl
    @BeforeEach
    void setUp() {
        underTest = new ProductServiceImpl(productRepository);
    }

    @Test
    void canAddProduct() throws BadRequestException {
//        given
        ProductEntity productEntity = TestDataUtil.createProductEntityA();
        //    when
        underTest.save(productEntity);
//        then
        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor =
                ArgumentCaptor.forClass(ProductEntity.class);
//        Првоеряем, что был вызвнан метод save
//        с переданным агрументом productEntityArgumentCaptor, то есть фактическим товаром
        verify(productRepository).save(productEntityArgumentCaptor.capture());

        ProductEntity capturedProduct = productEntityArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(productEntity);
    }

//    @Test
//    void willThrowWhenTitleIsTaken() {
////        give
//        ProductEntity productEntity = TestDataUtil.createProductEntityA();
//
//        when(productRepository.existsByTitle(productEntity.getTitle()))
//                .thenReturn(true);
//
////        when
////        then
//        assertThatThrownBy(() -> underTest.save(productEntity))
//                .isInstanceOf(BadRequestException.class)
//                .hasMessageContaining("Такой товар уже существует");
//    }



    @Test
    @Disabled
    void findAll() {
    }

    @Test
    @Disabled
    void isExists() {
    }

    @Test
    @Disabled
    void findProduct() {
    }

    @Test
    @Disabled
    void variableUpdate() {
    }

    @Test
    @Disabled
    void delete() {
    }

    @Test
    void canGetAllProducts() {
//        when
        underTest.findAllList();
//        then
        verify(productRepository).findAll();
    }

    @Test
    @Disabled
    void findTop12ByOrderByCountDesc() {
    }
}