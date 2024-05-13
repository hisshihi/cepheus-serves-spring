package com.example.web.cepheusservice.services.impl;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.OrderProduct;
import com.example.web.cepheusservice.domain.entity.ProductEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import com.example.web.cepheusservice.repositories.OrderEntityRepository;
import com.example.web.cepheusservice.repositories.ProductRepository;
import com.example.web.cepheusservice.repositories.UserRepository;
import com.example.web.cepheusservice.services.OrderEntityService;
import com.example.web.cepheusservice.services.ProductService;
import com.example.web.cepheusservice.services.UserServise;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderEntityServiceImpl implements OrderEntityService {

    private final OrderEntityRepository orderEntityRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public OrderEntity saveOrder(OrderEntity order, Principal principal) {
        // Получаем пользователя из Principal
        UserEntity user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        order.setUser(user);

        // Устанавливаем основные данные заказа
        order.setCity(order.getCity());
        order.setBuyer(order.getBuyer());
        order.setDeliveryMethod(order.getDeliveryMethod());
        order.setAllCountProduct(order.getAllCountProduct());
        order.setAllPrice(order.getAllPrice());

        // Очищаем текущий список продуктов в заказе
        List<OrderProduct> orderProducts = new ArrayList<>();

        // Добавляем продукты к заказу
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            // Получаем управляемую версию продукта из базы данных
            ProductEntity dbProduct = productRepository.findById(orderProduct.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            // Создаем новый объект связи OrderProduct
            OrderProduct newOrderProduct = new OrderProduct();
            newOrderProduct.setOrder(order);
            newOrderProduct.setProduct(dbProduct);
            newOrderProduct.setProductCounts(orderProduct.getProductCounts());

            // Добавляем к списку продуктов в заказе
            orderProducts.add(newOrderProduct);
        }

        // Присваиваем обновленный список продуктов заказу
        order.setOrderProducts(orderProducts);

        // Сохраняем заказ
        return orderEntityRepository.save(order);
    }
}
