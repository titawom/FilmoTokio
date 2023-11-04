package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Film;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FilmService {
    List<Film> getFilms();
    Film getFilmById(Long id);

    Film getFilmByTitle(String title);

    void deleteFilmById(Long id);

    void createFilm(Film film);

    void saveFilm(Film film);

}
