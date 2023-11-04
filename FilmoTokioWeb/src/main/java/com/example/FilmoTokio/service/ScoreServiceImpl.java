package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Score;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.repository.ScoreRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void saveScore(User user, Film film, int scoreValue) {
        List<Score> existingScore = scoreRepository.findByUserAndFilm(user, film);
        if (existingScore.size() > 0) {
            throw new IllegalStateException("El usuario ya ha puntuado este film.");
        }

        Score score = new Score();
        score.setUser(user);
        score.setFilm(film);
        score.setValue(scoreValue);

        scoreRepository.save(score);
    }

    @Override
    public boolean isScoreDone(Film film) {
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findByUsername(username);
        List<Score> score = scoreRepository.findByUserAndFilm(user.get(),film);
        if (score.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
