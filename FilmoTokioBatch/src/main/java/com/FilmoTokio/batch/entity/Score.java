package com.FilmoTokio.batch.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name =" Score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
