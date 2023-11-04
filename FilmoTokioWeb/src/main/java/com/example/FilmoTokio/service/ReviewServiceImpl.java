package com.example.FilmoTokio.service;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.repository.ReviewRepository;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveReview(ReviewRequest reviewRequest, Film film, User user) {

        Review review = new Review();
        review.setUser(user);
        review.setFilm(film);
        review.setTitle(reviewRequest.getTitle());
        review.setTextReview(reviewRequest.getReview());

        reviewRepository.save(review);
    }
   
    public List<Review> getReviewsByFilm (Film film) {
        return reviewRepository.findByFilm(film);
    }
}
