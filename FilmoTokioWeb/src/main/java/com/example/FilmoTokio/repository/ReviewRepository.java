package com.example.FilmoTokio.repository;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilm(Film film);
}
