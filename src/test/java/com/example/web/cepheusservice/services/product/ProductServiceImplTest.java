package com.example.web.cepheusservice.services.product;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.CategoryRepository;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.services.impl.CategoryServiceImpl;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
//class ProductServiceImplTest {
//
//    @Mock
//    private ProductRepository productRepository;
//    @Mock
//    private CategoryRepository categoryRepository;
//    private ProductServiceImpl underTest;
//    private CategoryServiceImpl categoryService;
//
////    Перед каждым тестом создаём новый экземпляр ProductServiceImpl
//    @BeforeEach
//    void setUp() {
//        underTest = new ProductServiceImpl(productRepository);
//        categoryService = new CategoryServiceImpl(categoryRepository);
//    }
//
//    @Test
//    void canAddProduct() throws BadRequestException {
////        given
//        ProductEntity productEntity = TestDataUtil.createProductEntityA();
//        //    when
//        underTest.save(productEntity);
////        then
//        ArgumentCaptor<ProductEntity> productEntityArgumentCaptor =
//                ArgumentCaptor.forClass(ProductEntity.class);
////        Првоеряем, что был вызвнан метод save
////        с переданным агрументом productEntityArgumentCaptor, то есть фактическим товаром
//        verify(productRepository).save(productEntityArgumentCaptor.capture());
//
//        ProductEntity capturedProduct = productEntityArgumentCaptor.getValue();
//
//        assertThat(capturedProduct).isEqualTo(productEntity);
//    }
//
//    @Test
//    void canGetProductByCategory() throws BadRequestException {
////        give
//        ProductEntity productEntity = TestDataUtil.createProductEntityA();
//        ProductEntity productEntity2 = TestDataUtil.createProductEntityB();
//        CategoryEntity categoryEntity = TestDataUtil.createTestCategoryA();
//        Pageable pageable = PageRequest.of(0, 2);
//        List<ProductEntity> expectedProductEntity = Arrays.asList(productEntity, productEntity2);
////        when
//        when(productRepository.findAllByCategoryEntityId(categoryEntity.getId(), pageable)).thenReturn(new PageImpl<>(expectedProductEntity));
//        underTest.save(productEntity);
//        categoryService.save(categoryEntity);
//
////        then
//        Page<ProductEntity> result = underTest.filterByCategory(categoryEntity.getId(), pageable);
//        assertThat(result.getContent().get(0)).isEqualTo(expectedProductEntity.get(0));
//
//    }
//
////    @Test
////    void willThrowWhenTitleIsTaken() {
//////        give
////        ProductEntity productEntity = TestDataUtil.createProductEntityA();
////
////        when(productRepository.existsByTitle(productEntity.getTitle()))
////                .thenReturn(true);
////
//////        when
//////        then
////        assertThatThrownBy(() -> underTest.save(productEntity))
////                .isInstanceOf(BadRequestException.class)
////                .hasMessageContaining("Такой товар уже существует");
////    }
//
//
//
//    @Test
//    @Disabled
//    void findAll() {
//    }
//
//    @Test
//    @Disabled
//    void isExists() {
//    }
//
//    @Test
//    @Disabled
//    void findProduct() {
//    }
//
//    @Test
//    @Disabled
//    void variableUpdate() {
//    }
//
//    @Test
//    @Disabled
//    void delete() {
//    }
//
//    @Test
//    void canGetAllProducts() {
////        when
//        underTest.findAllList();
////        then
//        verify(productRepository).findAll();
//    }
//
//    @Test
//    @Disabled
//    void findTop12ByOrderByCountDesc() {
//    }
//}