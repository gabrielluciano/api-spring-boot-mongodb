package com.gabrielluciano.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gabrielluciano.api.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);
}
