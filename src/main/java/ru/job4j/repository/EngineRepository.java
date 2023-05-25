package ru.job4j.repository;

import ru.job4j.model.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {

    Engine create(Engine engine);

    void update(Engine engine);

    void delete(int engineId);

    List<Engine> findAllOrderById();

    Optional<Engine> findById(int engineId);
}
