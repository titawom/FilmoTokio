package com.example.FilmoTokio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private Integer duration;
    private String sypnosis;
    private String poster;
    private boolean migrate;
    private LocalDate dateMigrate;



    @OneToMany(mappedBy = "film")
    private Set<Score> score;

    @OneToMany(mappedBy = "film")
    private Set<Review> reviews;
 
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person director;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> musician;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> screenwriter;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> photographer; 

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Person> actor; 
}
