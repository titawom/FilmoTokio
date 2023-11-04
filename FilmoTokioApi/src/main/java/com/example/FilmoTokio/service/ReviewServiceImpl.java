package com.example.FilmoTokio.service;

import com.example.FilmoTokio.DTO.ReviewRequest;
import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.entity.Review;
import com.example.FilmoTokio.entity.User;
import com.example.FilmoTokio.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
   
    public List<Review> getReviewsByFilm (Film film) {
        return reviewRepository.findByFilm(film);
    }

    @Override
    public void saveReview(ReviewRequest reviewRequest, User user, Film film) {

        Review review = new Review();

        review.setUser(user);
        review.setFilm(film);
        review.setTitle(reviewRequest.getTitle());
        review.setTextReview(reviewRequest.getTextReview());

        reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review;
    }

    @Override
    public Optional<Review> getReviewsByFilmAndUser(Film film, User user) throws UnsupportedOperationException {
        Optional<Review> review = reviewRepository.findByFilmAndUser(film, user);
        if (review.isPresent()) {
            throw new UnsupportedOperationException("El usuario " + user.getUsername() + " ya ha creado crítica para esta película");
        }
        return review;
    }

}
