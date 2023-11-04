package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.User;

public interface ScoreService {
    void saveScore(User user, Film film, int scoreValue);
    boolean isScoreDone (Film film);
}
