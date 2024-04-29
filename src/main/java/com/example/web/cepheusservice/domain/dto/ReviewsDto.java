package com.example.web.cepheusservice.domain.dto;

import com.example.web.cepheusservice.domain.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsDto  {

    private Long id;
    private String text;
    private int rating;
//    private int likeCount;
//    private int dislikeCount;

    private LocalDateTime created_at;

    private UserEntity user;

}
