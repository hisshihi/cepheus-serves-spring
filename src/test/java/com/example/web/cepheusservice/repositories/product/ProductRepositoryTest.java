package com.example.web.cepheusservice.repositories.product;

import com.example.web.cepheusservice.TestDataUtil;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository underTest;

    @Test
    void isEheProductWithTheLargestQuantityBeingDisplayed() {
//        give
        ProductEntity productEntityA = TestDataUtil.createProductEntityA();
        ProductEntity productEntityB = TestDataUtil.createProductEntityB();

        underTest.save(productEntityA);
        underTest.save(productEntityB);
        Pageable pageable = PageRequest.of(0, 2);
//        when
        Page<ProductEntity> products = underTest.findByOrderByCountDesc(pageable);
//        then
        assertThat(products.getContent().get(0)).isEqualTo(productEntityB);
    }

}
