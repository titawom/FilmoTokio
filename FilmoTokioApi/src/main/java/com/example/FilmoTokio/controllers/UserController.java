package com.example.FilmoTokio.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.service.FilmService;
import com.example.FilmoTokio.service.ReviewService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private FilmService filmService;

    @GetMapping("/{id}/reviews")
    public String userReviews (@PathVariable("id") Long id) {
        
        Film film = filmService.getFilmById(id);
        List<Review> review = reviewService.getReviewsByFilm(film);
        String result = "Film: " + film.getTitle() + "\n";
        for(Review r : review)
        {
            result = result + "Title: " + r.getTitle() + " Text: " + r.getTextReview() + "\n";
        }
        return result;
    } 
}
