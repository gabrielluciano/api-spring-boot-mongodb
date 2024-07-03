package com.gabrielluciano.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabrielluciano.api.domain.User;
import com.gabrielluciano.api.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
