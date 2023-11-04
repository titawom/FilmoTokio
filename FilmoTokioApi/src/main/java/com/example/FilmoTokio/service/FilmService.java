package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Film;
import org.springframework.stereotype.Service;

@Service
public interface FilmService {

    Film getFilmById(Long id);
}
