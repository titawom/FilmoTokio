package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Person;
import org.springframework.stereotype.Service;


@Service
public interface PersonService {
    void createPerson(Person person);
}
