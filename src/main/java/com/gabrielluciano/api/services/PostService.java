package com.gabrielluciano.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabrielluciano.api.domain.Post;
import com.gabrielluciano.api.repository.PostRepository;
import com.gabrielluciano.api.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
