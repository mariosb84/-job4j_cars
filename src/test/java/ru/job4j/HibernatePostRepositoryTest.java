package ru.job4j;

import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HibernateConfiguration;
import ru.job4j.model.Car;
import ru.job4j.model.File;
import ru.job4j.model.Post;
import ru.job4j.repository.HibernateCrudRepository;
import ru.job4j.repository.HibernatePostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernatePostRepositoryTest {

    @Test
    public void whenCreateNewPost() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
            Post post = new Post();
            post.setDescription("12345");
            hibernatePostRepository.create(post);
            Optional<Post> result = hibernatePostRepository.findById(post.getId());
            assertThat(result.get().getDescription()).isEqualTo(post.getDescription());

    }

    @Test
    public void whenUpdatePost() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Post post = new Post();
        post.setDescription("12345");
        hibernatePostRepository.create(post);
        post.setDescription("1234567");
        hibernatePostRepository.update(post);
        Optional<Post> result = hibernatePostRepository.findById(post.getId());
        assertThat(result.get().getDescription()).isEqualTo("1234567");

    }

    @Test
    public void whenDeletePost() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Post post = new Post();
        post.setDescription("12345");
        hibernatePostRepository.create(post);
        var id = post.getId();
        hibernatePostRepository.delete(id);
        Optional<Post> result = hibernatePostRepository.findById(id);
        assertThat(result.isEmpty());

    }

    @Test
    public void whenFindAllOrderByIdItems() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Post post = new Post();
        post.setDescription("12345");
        hibernatePostRepository.create(post);
        Post post2 = new Post();
        post2.setDescription("123456");
        hibernatePostRepository.create(post2);
        Post post3 = new Post();
        post3.setDescription("1234567");
        hibernatePostRepository.create(post3);
            List<Post> list = List.of(post, post2, post3);
            List<Post> result = hibernatePostRepository.findAllOrderById();
            assertThat(result.containsAll(list)).isEqualTo(true);

        }

    @Test
    public void whenFindById() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Post post = new Post();
        post.setDescription("12345");
        hibernatePostRepository.create(post);
        Optional<Post> result = hibernatePostRepository.findById(post.getId());
        assertThat(result.get().getDescription()).isEqualTo(post.getDescription());

    }

    @Test
    public void whenFindByLikeModelCarItems() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Car car = new Car();
        car.setName("Audi");
        Post post = new Post();
        post.setCar(car);
        hibernatePostRepository.create(post);
        List<Post> list = List.of(post);
        List<Post> result = hibernatePostRepository.findByLikeModelCar(post.getCar().getName());
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Test
    public void whenFindByCarWithPhotoItems() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        File file = new File();
        Car car = new Car();
        Post post = new Post();
        post.setCar(car);
        post.setPhoto(file);
        hibernatePostRepository.create(post);
        List<Post> list = List.of(post);
        List<Post> result = hibernatePostRepository.findByCarWithPhoto();
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Test
    public void whenFindByDateLastDayItems() {
        var hibernatePostRepository = new HibernatePostRepository((HibernateCrudRepository) new HibernateConfiguration().sf());
        Post post = new Post();
        Post post2 = new Post();
        post2.setCreated(LocalDateTime.now().minusHours(48));
        hibernatePostRepository.create(post);
        hibernatePostRepository.create(post2);
        List<Post> list = List.of(post, post2);
        List<Post> result = hibernatePostRepository.findByDateLastDay();
        assertThat(result.containsAll(list)).isEqualTo(false);

    }


}
