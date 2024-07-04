package com.gabrielluciano.api.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielluciano.api.domain.Post;
import com.gabrielluciano.api.domain.User;
import com.gabrielluciano.api.repository.PostRepository;
import com.gabrielluciano.api.repository.UserRepository;

@Configuration
public class DatasourceConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DatasourceConfig(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, LocalDate.parse("21/03/2018", fmt), "Partiu viagem",
                "Vou viajar para São Paulo. Abraçcos!", maria);
        Post post2 = new Post(null, LocalDate.parse("23/03/2018", fmt), "Bom dia",
                "Acordei feliz hoje!", maria);

        userRepository.saveAll(List.of(maria, alex, bob));
        postRepository.saveAll(List.of(post1, post2));
    }
}
