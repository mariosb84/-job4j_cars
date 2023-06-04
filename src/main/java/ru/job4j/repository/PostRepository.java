package ru.job4j.repository;

import ru.job4j.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post create(Post post);

    void update(Post post);

    void delete(int postId);

    List<Post> findAllOrderById();

    Optional<Post> findById(int postId);

    List<Post> findByLikeModelCar(String key);

    List<Post> findByCarWithPhoto();

    List<Post> findByDateLastDay();

}
