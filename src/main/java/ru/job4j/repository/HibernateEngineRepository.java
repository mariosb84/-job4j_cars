package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateEngineRepository implements EngineRepository {
    private final HibernateCrudRepository hibernateCrudRepository;

    /**
     * Сохранить в базе.
     * @param engine engine.
     * @return engine с id.
     */
    public Engine create(Engine engine) {
        hibernateCrudRepository.run(session -> session.persist(engine));
        return engine;
    }

    /**
     * Обновить в базе engine.
     * @param engine engine.
     */
    public void update(Engine engine) {
        hibernateCrudRepository.run(session -> session.merge(engine));
    }

    /**
     * Удалить engine по id.
     * @param engineId ID
     */
    public void delete(int engineId) {
        hibernateCrudRepository.run(
                "delete from Engine where id = :fId",
                Map.of("fId", engineId)
        );
    }

    /**
     * Список engines отсортированных по id.
     * @return список engines.
     */
    public List<Engine> findAllOrderById() {
        return hibernateCrudRepository.query("from Engine order by id asc", Engine.class);
    }

    /**
     * Найти engine по ID
     * @return engine.
     */
    public Optional<Engine> findById(int engineId) {
        return hibernateCrudRepository.optional(
                "from Engine where id = :fId", Engine.class,
                Map.of("fId", engineId)
        );
    }

}