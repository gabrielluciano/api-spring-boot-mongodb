package com.gabrielluciano.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gabrielluciano.api.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> findByTitle(String text);

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query("{ $and: [{ date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [{ 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments': { $elemMatch: { 'text': { $regex: ?0, $options: 'i' } } } }] }] }")
    List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate);
}
