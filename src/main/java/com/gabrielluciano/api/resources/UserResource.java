package com.gabrielluciano.api.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielluciano.api.dto.UserDTO;
import com.gabrielluciano.api.services.UserService;

@RestController
@RequestMapping("users")
public class UserResource {

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = service.findAll().stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }
}
