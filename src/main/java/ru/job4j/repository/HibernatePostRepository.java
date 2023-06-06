package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernatePostRepository implements PostRepository {

    private final HibernateCrudRepository hibernateCrudRepository;
    private final LocalDateTime getDateTimeLastDay = LocalDateTime.now().minusHours(24);

    /**
     * Сохранить в базе.
     * @param post post.
     * @return post с id.
     */
    public Post create(Post post) {
        hibernateCrudRepository.run(session -> session.persist(post));
        return post;
    }

    /**
     * Обновить в базе post.
     * @param post post.
     */
    public void update(Post post) {
        hibernateCrudRepository.run(session -> session.merge(post));
    }

    /**
     * Удалить post по id.
     * @param postId ID
     */
    public void delete(int postId) {
        hibernateCrudRepository.run(
                "delete from Post where id = :fId",
                Map.of("fId", postId)
        );
    }

    /**
     * Список posts отсортированных по id.
     * @return список posts.
     */
    public List<Post> findAllOrderById() {
        return hibernateCrudRepository.query("from Post order by id asc", Post.class);
    }

    /**
     * Найти post по ID
     * @return post.
     */
    public Optional<Post> findById(int postId) {
        return hibernateCrudRepository.optional(
                "from Post where id = :fId", Post.class,
                Map.of("fId", postId)
        );
    }

    /**
     * Список posts по car.name == key
     * @param key key
     * @return список posts.
     */
    public List<Post> findByLikeModelCar(String key) {
        return hibernateCrudRepository.query(
                "from Post as post where post.car.name = :fKey", Post.class,
                Map.of("fKey", key)
        );
    }

    /**
     * Список posts с фото car
     * @return список posts.
     */
    public List<Post> findByCarWithPhoto() {
        return hibernateCrudRepository.query(
                "from Post as post where post.photo is not null", Post.class);
    }

    /**
     * Список posts за последний день
     * @return список posts.
     */
    public List<Post> findByDateLastDay() {
        return hibernateCrudRepository.query(
                "from Post as post where post.created >= fDate", Post.class,
                Map.of("fDate", getDateTimeLastDay)
        );
    }

}
