package com.gabrielluciano.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabrielluciano.api.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
