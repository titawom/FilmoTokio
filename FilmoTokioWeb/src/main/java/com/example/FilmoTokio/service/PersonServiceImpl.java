package com.example.FilmoTokio.service;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FilmoTokio.entity.Person;
import com.example.FilmoTokio.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public void createPerson(Person person) {
        personRepository.save(person);
    }
    
}
