package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import com.example.web.cepheusservice.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUserId(Long id);
}
