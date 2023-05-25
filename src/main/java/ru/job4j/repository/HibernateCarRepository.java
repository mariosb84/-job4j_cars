package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateCarRepository implements CarRepository {
    private final HibernateCrudRepository hibernateCrudRepository;

    /**
     * Сохранить в базе.
     * @param car car.
     * @return car с id.
     */
    public Car create(Car car) {
        hibernateCrudRepository.run(session -> session.persist(car));
        return car;
    }

    /**
     * Обновить в базе car.
     * @param car car.
     */
    public void update(Car car) {
        hibernateCrudRepository.run(session -> session.merge(car));
    }

    /**
     * Удалить car по id.
     * @param carId ID
     */
    public void delete(int carId) {
        hibernateCrudRepository.run(
                "delete from Car where id = :fId",
                Map.of("fId", carId)
        );
    }

    /**
     * Список cars отсортированных по id.
     * @return список cars.
     */
    public List<Car> findAllOrderById() {
        return hibernateCrudRepository.query("from Car order by id asc", Car.class);
    }

    /**
     * Найти car по ID
     * @return car.
     */
    public Optional<Car> findById(int carId) {
        return hibernateCrudRepository.optional(
                "from Car where id = :fId", Car.class,
                Map.of("fId", carId)
        );
    }

}