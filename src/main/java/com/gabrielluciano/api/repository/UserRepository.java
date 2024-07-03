package com.gabrielluciano.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabrielluciano.api.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
