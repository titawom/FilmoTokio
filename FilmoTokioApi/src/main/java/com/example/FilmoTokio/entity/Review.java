package com.example.FilmoTokio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String textReview;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
