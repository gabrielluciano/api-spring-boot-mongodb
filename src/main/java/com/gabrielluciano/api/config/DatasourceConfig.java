package com.gabrielluciano.api.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielluciano.api.domain.Post;
import com.gabrielluciano.api.domain.User;
import com.gabrielluciano.api.dto.AuthorDTO;
import com.gabrielluciano.api.dto.CommentDTO;
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

        userRepository.saveAll(List.of(maria, alex, bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2018", fmt), "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));

        Post post2 = new Post(null, LocalDate.parse("23/03/2018", fmt), "Bom dia",
                "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano", LocalDate.parse("21/03/2018", fmt), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite", LocalDate.parse("23/03/2018", fmt), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia", LocalDate.parse("23/03/2018", fmt), new AuthorDTO(alex));

        post1.getComments().addAll(List.of(c1, c2));
        post2.getComments().add(c3);

        postRepository.saveAll(List.of(post1, post2));

        maria.getPosts().addAll(List.of(post1, post2));

        userRepository.save(maria);
    }
}
