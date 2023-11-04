package com.example.FilmoTokio.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.service.FilmService;
import com.example.FilmoTokio.service.ReviewService;
import com.example.FilmoTokio.service.UserService;

@RestController
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private FilmService filmService;

    @PostMapping("/new-review")
    public ResponseEntity<String> userReviews (@RequestBody ReviewRequest reviewRequest) {
        
        User user = userService.findById(reviewRequest.getUserId()).get();
        Film film = filmService.getFilmById(reviewRequest.getFilmId());
        try {
            Optional<Review> review = reviewService.getReviewsByFilmAndUser(film, user);
            if (!review.isPresent()) {
            reviewService.saveReview(reviewRequest, user, film);
            return ResponseEntity.ok("Crítica creada para la película " + film.getTitle());     
        }
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
        return null;
    } 

}
