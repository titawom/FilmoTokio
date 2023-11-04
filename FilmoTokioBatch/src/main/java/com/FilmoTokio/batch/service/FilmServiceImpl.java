package com.FilmoTokio.batch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FilmoTokio.batch.entity.Film;
import com.FilmoTokio.batch.repository.FilmRepository;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<Film> getFilmsNotMigrated() {
        return filmRepository.findAll();
    }

}
