package com.example.FilmoTokio.service;

import java.util.Optional;

import com.example.FilmoTokio.entity.User;

public interface UserService {
    Optional<User> findById (Long id);
}