package com.example.FilmoTokio.service;

import java.util.List;
import java.util.Optional;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;

public interface ReviewService {

    void saveReview(ReviewRequest reviewRequest, User user, Film film);
    List<Review> getReviewsByFilm (Film film);
    Optional<Review> getReviewById (Long id);
    Optional<Review> getReviewsByFilmAndUser (Film film, User user);

}
