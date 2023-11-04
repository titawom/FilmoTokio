package com.FilmoTokio.batch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.FilmoTokio.batch.entity.enums.TypePersonEnum;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypePersonEnum type;
    
    @OneToMany(mappedBy = "director")
    private Set<Film> filmsDirector; 
    
    @ManyToMany(mappedBy = "musician")
    private Set<Film> filmsMusician; 

    @ManyToMany(mappedBy = "screenwriter")
    private Set<Film> filmsScreenwriter;

    @ManyToMany(mappedBy = "photographer")
    private Set<Film> filmsPhotographer;

    @ManyToMany(mappedBy = "actor")
    private Set<Film> filmsActor; 
}
