package com.example.web.cepheusservice.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_reviews")
public class ReviewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String text;
    @Column(length = 5)
    private int rating;
    private int likeCount;
    private int dislikeCount;
    @CreationTimestamp
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String formattedDate;

    public void setCreationDate() {
        this.date = LocalDateTime.from(Instant.now());
    }
}
