package com.gabrielluciano.api.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielluciano.api.domain.Post;
import com.gabrielluciano.api.domain.User;
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

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(new UserDTO(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) {
        User user = service.fromDTO(userDTO);
        user = service.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = service.fromDTO(userDTO);
        user.setId(id);
        user = service.update(user);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id).getPosts());
    }
}
