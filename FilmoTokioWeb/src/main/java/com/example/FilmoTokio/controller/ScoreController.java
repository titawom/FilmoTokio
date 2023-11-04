package com.example.FilmoTokio.controller;

import com.example.FilmoTokio.DTO.ScoreRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.service.FilmService;
import com.example.FilmoTokio.service.ScoreService;
import com.example.FilmoTokio.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @Autowired
    FilmService filmService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveScore(ScoreRequest scoreRequest) {
        // Obtener los datos necesarios de la solicitud (por ejemplo, el usuario, la película y la puntuación)
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findByUsername(username);
        Film film = filmService.getFilmById(scoreRequest.getFilmId());
        int scoreValue = scoreRequest.getScoreValue();

        // Llamar al servicio para guardar la puntuación
        scoreService.saveScore(user.get(), film, scoreValue);

        // Devolver una respuesta exitosa
        return ResponseEntity.ok("Puntuación guardada correctamente");
    }
}
