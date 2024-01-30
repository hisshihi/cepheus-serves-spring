package com.example.web.cepheusservice;

import com.example.web.cepheusservice.domain.entity.CategoryEntity;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.services.CategoryService;
import com.example.web.cepheusservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DataBaseApplicationTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @Test
    public void createCategoryTest() {
        CategoryEntity categoryEntity = TestDataUtil.createTestCategory();
        CategoryEntity categoryEntityA = TestDataUtil.createTestCategoryA();
        categoryService.save(categoryEntity);
        CategoryEntity findCategory = categoryService.findById(categoryEntity);
        assertThat(findCategory.getId()).isEqualTo(categoryEntity.getId());
        assertThat(findCategory.getTitle()).isEqualTo(categoryEntity.getTitle());

    }

    @Test
    public void createProductTest() {
        ProductEntity productEntity = TestDataUtil.createProductEntity();
        productService.save(productEntity);
        ProductEntity findProduct = productService.findProduct(productEntity.getId()).orElseThrow(() -> new RuntimeException("Такого товара не существует"));
        assertThat(findProduct.getId()).isEqualTo(productEntity.getId());
    }

}
