package com.FilmoTokio.batch.service;

import org.springframework.stereotype.Service;

import com.FilmoTokio.batch.entity.Film;

import java.util.List;
@Service
public interface FilmService {
    
    List<Film> getFilmsNotMigrated();
}
