package com.gabrielluciano.api.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielluciano.api.domain.Post;
import com.gabrielluciano.api.resources.util.URL;
import com.gabrielluciano.api.services.PostService;

@RestController
@RequestMapping("posts")
public class PostResource {

    private final PostService service;

    public PostResource(PostService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(name = "text", defaultValue = "") String text) {
        return ResponseEntity.ok(service.findByTitle(text));
    }

    @GetMapping("fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(name = "text", defaultValue = "") String text,
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
        LocalDate min = URL.convertDate(minDate, LocalDate.ofEpochDay(0));
        LocalDate max = URL.convertDate(maxDate, LocalDate.now());
        return ResponseEntity.ok(service.fullSearch(text, min, max));
    }
}
