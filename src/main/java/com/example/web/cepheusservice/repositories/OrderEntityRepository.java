package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {

}
