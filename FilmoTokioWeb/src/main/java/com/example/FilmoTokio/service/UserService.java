package com.example.FilmoTokio.service;

import java.util.List;
import java.util.Optional;

import com.example.FilmoTokio.entity.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User usuario);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername (String username);

}