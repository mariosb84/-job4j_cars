package ru.job4j;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HibernateConfiguration;
import ru.job4j.model.User;
import ru.job4j.repository.HibernateCrudRepository;
import ru.job4j.repository.HibernateUserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateUserRepositoryTest {

    HibernateUserRepository hibernateUserRepository =
            new HibernateUserRepository((HibernateCrudRepository) new HibernateConfiguration().sf());

    @Disabled
    @Test
    public void whenCreateNewUser() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        Optional<User> result = hibernateUserRepository.findById(user.getId());
        assertThat(result.get().getLogin()).isEqualTo(user.getLogin());

    }

    @Disabled
    @Test
    public void whenUpdateUser() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        user.setLogin("1234567");
        hibernateUserRepository.update(user);
        Optional<User> result = hibernateUserRepository.findById(user.getId());
        assertThat(result.get().getLogin()).isEqualTo("1234567");

    }

    @Disabled
    @Test
    public void whenDeleteUser() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        var id = user.getId();
        hibernateUserRepository.delete(id);
        Optional<User> result = hibernateUserRepository.findById(id);
        assertThat(result.isEmpty());

    }

    @Disabled
    @Test
    public void whenFindAllOrderByIdUsers() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        User user2 = new User();
        user2.setLogin("123456");
        hibernateUserRepository.create(user2);
        User user3 = new User();
        user3.setLogin("1234567");
        hibernateUserRepository.create(user3);
        List<User> list = List.of(user, user2, user3);
        List<User> result = hibernateUserRepository.findAllOrderById();
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Disabled
    @Test
    public void whenFindById() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        Optional<User> result = hibernateUserRepository.findById(user.getId());
        assertThat(result.get().getLogin()).isEqualTo(user.getLogin());

    }

    @Disabled
    @Test
    public void whenFindByLikeLoginUsers() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        User user2 = new User();
        user2.setLogin("1234567");
        hibernateUserRepository.create(user2);
        List<User> list = List.of(user, user2);
        List<User> result = hibernateUserRepository.findByLikeLogin("123");
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Disabled
    @Test
    public void whenFindByLogin() {
        User user = new User();
        user.setLogin("12345");
        hibernateUserRepository.create(user);
        Optional<User> result = hibernateUserRepository.findByLogin(user.getLogin());
        assertThat(result.get().getLogin()).isEqualTo(user.getLogin());

    }

}
