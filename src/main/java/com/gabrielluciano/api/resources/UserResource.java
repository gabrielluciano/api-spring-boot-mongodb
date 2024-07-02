package com.gabrielluciano.api.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielluciano.api.domain.User;

@RestController
@RequestMapping("users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1", "Maria Silva", "maria@gmail.com");
        User alex = new User("2", "Alex Green", "alex@gmail.com");
        List<User> list = List.of(maria, alex);

        return ResponseEntity.ok(list);
    }
}
