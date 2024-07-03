package com.gabrielluciano.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabrielluciano.api.domain.User;
import com.gabrielluciano.api.dto.UserDTO;
import com.gabrielluciano.api.repository.UserRepository;
import com.gabrielluciano.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public User update(User data) {
        User user = findById(data.getId());
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        return repository.save(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
