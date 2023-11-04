package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Film;
import com.example.FilmoTokio.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film getFilmById(Long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        return optionalFilm.orElse(null);
    }

    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    @Override
    public void createFilm(Film film) {
        filmRepository.save(film);
    }

    @Override
    public void saveFilm(Film film) {

        filmRepository.save(film);
    }

    @Override
    public Film getFilmByTitle(String title) {
        return filmRepository.findByTitle(title);
    }
}
