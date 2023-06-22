package ru.job4j;

import org.junit.jupiter.api.Test;
import ru.job4j.configuration.HibernateConfiguration;
import ru.job4j.model.Owner;
import ru.job4j.repository.HibernateCrudRepository;
import ru.job4j.repository.HibernateOwnerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateOwnerRepositoryTest {

    HibernateOwnerRepository hibernateOwnerRepository =
            new HibernateOwnerRepository((HibernateCrudRepository) new HibernateConfiguration().sf());

    @Test
    public void whenCreateNewOwner() {
        Owner owner = new Owner();
        owner.setName("12345");
        hibernateOwnerRepository.create(owner);
        Optional<Owner> result = hibernateOwnerRepository.findById(owner.getId());
        assertThat(result.get().getName()).isEqualTo(owner.getName());

    }

    @Test
    public void whenUpdateOwner() {
        Owner owner = new Owner();
        owner.setName("12345");
        hibernateOwnerRepository.create(owner);
        owner.setName("1234567");
        hibernateOwnerRepository.update(owner);
        Optional<Owner> result =  hibernateOwnerRepository.findById(owner.getId());
        assertThat(result.get().getName()).isEqualTo("1234567");

    }

    @Test
    public void whenDeleteOwner() {
        Owner owner = new Owner();
        owner.setName("12345");
        hibernateOwnerRepository.create(owner);
        var id = owner.getId();
        hibernateOwnerRepository.delete(id);
        Optional<Owner> result = hibernateOwnerRepository.findById(id);
        assertThat(result.isEmpty());

    }

    @Test
    public void whenFindAllOrderByIdOwners() {
        Owner owner = new Owner();
        owner.setName("12345");
        hibernateOwnerRepository.create(owner);
        Owner owner2 = new Owner();
        owner2.setName("123456");
        hibernateOwnerRepository.create(owner2);
        Owner owner3 = new Owner();
        owner3.setName("1234567");
        hibernateOwnerRepository.create(owner3);
        List<Owner> list = List.of(owner, owner2, owner3);
        List<Owner> result = hibernateOwnerRepository.findAllOrderById();
        assertThat(result.containsAll(list)).isEqualTo(true);

    }

    @Test
    public void whenFindById() {
        Owner owner = new Owner();
        owner.setName("12345");
        hibernateOwnerRepository.create(owner);
        Optional<Owner> result = hibernateOwnerRepository.findById(owner.getId());
        assertThat(result.get().getName()).isEqualTo(owner.getName());

    }

}
