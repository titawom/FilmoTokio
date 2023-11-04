package com.example.FilmoTokio.repository;

import com.example.FilmoTokio.entity.Film;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film findByTitle(String title);
    Optional<Film> findById (Long id);
}
