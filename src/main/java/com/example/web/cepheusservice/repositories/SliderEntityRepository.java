package com.example.web.cepheusservice.repositories;

import com.example.web.cepheusservice.domain.entity.SliderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderEntityRepository extends JpaRepository<SliderEntity, Long> {
}
