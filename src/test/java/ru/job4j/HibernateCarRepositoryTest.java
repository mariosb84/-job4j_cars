package ru.job4j;

import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HibernateConfiguration;
import ru.job4j.model.Car;
import ru.job4j.repository.HibernateCarRepository;
import ru.job4j.repository.HibernateCrudRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateCarRepositoryTest {

    HibernateCarRepository hibernateCarRepository =
            new HibernateCarRepository((HibernateCrudRepository) new HibernateConfiguration().sf());

    @Test
    public void whenCreateNewCar() {
        Car car = new Car();
        car.setName("bmw");
        hibernateCarRepository.create(car);
        Optional<Car> result = hibernateCarRepository.findById(car.getId());
        assertThat(result.get().getName()).isEqualTo(car.getName());

    }

    @Test
    public void whenUpdateCar() {
        Car car = new Car();
        car.setName("bmw");
        hibernateCarRepository.create(car);
        car.setName("opel");
        hibernateCarRepository.update(car);
        Optional<Car> result =  hibernateCarRepository.findById(car.getId());
        assertThat(result.get().getName()).isEqualTo("opel");

    }

    @Test
    public void whenDeleteCar() {
        Car car = new Car();
        car.setName("bmw");
        hibernateCarRepository.create(car);
        var id = car.getId();
        hibernateCarRepository.delete(id);
        Optional<Car> result = hibernateCarRepository.findById(id);
        assertThat(result.isEmpty());

    }

    @Test
    public void whenFindAllOrderByIdCars() {
        Car car = new Car();
        car.setName("bmw");
        hibernateCarRepository.create(car);
        Car car2 = new Car();
        car2.setName("opel");
        hibernateCarRepository.create(car2);
        Car car3 = new Car();
        car3.setName("chevrolet");
        hibernateCarRepository.create(car3);
        List<Car> list = List.of(car, car2, car3);
        List<Car> result = hibernateCarRepository.findAllOrderById();
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Test
    public void whenFindById() {
        Car car = new Car();
        car.setName("bmw");
        hibernateCarRepository.create(car);
        Optional<Car> result = hibernateCarRepository.findById(car.getId());
        assertThat(result.get().getName()).isEqualTo(car.getName());
    }

}
