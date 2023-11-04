package com.example.FilmoTokio.repository;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Score;
import com.example.FilmoTokio.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByUserAndFilm(User user, Film film);
}
