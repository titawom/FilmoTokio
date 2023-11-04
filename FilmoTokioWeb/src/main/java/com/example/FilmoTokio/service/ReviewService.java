package com.example.FilmoTokio.service;

import java.util.List;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;

public interface ReviewService {
    void saveReview(ReviewRequest reviewRequest, Film film, User user);

    List<Review> getReviewsByFilm(Film film);
}
