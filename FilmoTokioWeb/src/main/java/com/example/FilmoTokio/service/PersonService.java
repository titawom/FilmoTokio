package com.example.FilmoTokio.service;

import com.example.FilmoTokio.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {
    List<Person> getPersons();
    Person getPersonById(Long id);
    Person savePerson(Person person);
    void deletePersonById(Long id);

    void createPerson(Person person);
    // Otros métodos según tus necesidades
}
