package com.example.FilmoTokio.controller;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.DTO.ScoreRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.service.FilmService;
import com.example.FilmoTokio.service.ReviewService;
import com.example.FilmoTokio.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/reviews")
public class ReviewController {
   
     @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @Autowired
    FilmService filmService;

    @Autowired
    ReviewService reviewService;

     @PostMapping("/save")
    public ResponseEntity<String> saveScore(ReviewRequest reviewRequest) {
        // Obtener los datos necesarios de la solicitud (por ejemplo, el usuario, la película y la puntuación)
        String username = (String) session.getAttribute("username");
        Optional<User> user = userService.findByUsername(username);
        Film film = filmService.getFilmById(reviewRequest.getFilmId());

        // Llamar al servicio para guardar la puntuación
        reviewService.saveReview(reviewRequest, film, user.get());

        // Devolver una respuesta exitosa
        return ResponseEntity.ok("Review guardada correctamente");
    }
  
}
