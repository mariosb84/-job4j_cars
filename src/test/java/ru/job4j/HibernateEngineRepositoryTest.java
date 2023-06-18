package ru.job4j;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HibernateConfiguration;
import ru.job4j.model.Engine;
import ru.job4j.repository.HibernateCrudRepository;
import ru.job4j.repository.HibernateEngineRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateEngineRepositoryTest {

    HibernateEngineRepository hibernateEngineRepository =
            new HibernateEngineRepository((HibernateCrudRepository) new HibernateConfiguration().sf());

    @Disabled
    @Test
    public void whenCreateNewEngine() {
        Engine engine = new Engine();
        engine.setName("12345");
        hibernateEngineRepository.create(engine);
        Optional<Engine> result = hibernateEngineRepository.findById(engine.getId());
        assertThat(result.get().getName()).isEqualTo(engine.getName());

    }

    @Disabled
    @Test
    public void whenUpdateEngine() {
        Engine engine = new Engine();
        engine.setName("12345");
        hibernateEngineRepository.create(engine);
        engine.setName("1234567");
        hibernateEngineRepository.update(engine);
        Optional<Engine> result =  hibernateEngineRepository.findById(engine.getId());
        assertThat(result.get().getName()).isEqualTo("1234567");

    }

    @Disabled
    @Test
    public void whenDeleteEngine() {
        Engine engine = new Engine();
        engine.setName("12345");
        hibernateEngineRepository.create(engine);
        var id = engine.getId();
        hibernateEngineRepository.delete(id);
        Optional<Engine> result = hibernateEngineRepository.findById(id);
        assertThat(result.isEmpty());

    }

    @Disabled
    @Test
    public void whenFindAllOrderByIdEngines() {
        Engine engine = new Engine();
        engine.setName("12345");
        hibernateEngineRepository.create(engine);
        Engine engine2 = new Engine();
        engine2.setName("123456");
        hibernateEngineRepository.create(engine2);
        Engine engine3 = new Engine();
        engine3.setName("1234567");
        hibernateEngineRepository.create(engine3);
        List<Engine> list = List.of(engine, engine2, engine3);
        List<Engine> result = hibernateEngineRepository.findAllOrderById();
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Disabled
    @Test
    public void whenFindById() {
        Engine engine = new Engine();
        engine.setName("12345");
        hibernateEngineRepository.create(engine);
        Optional<Engine> result = hibernateEngineRepository.findById(engine.getId());
        assertThat(result.get().getName()).isEqualTo(engine.getName());

    }
}
