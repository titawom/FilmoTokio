package com.example.FilmoTokio.repository;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFilm(Film film);
    Optional<Review> findById (Long id);
    Optional<Review> findByFilmAndUser (Film film, User user);
}
