package ru.job4j.repository;

import ru.job4j.model.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    Owner create(Owner owner);

    void update(Owner owner);

    void delete(int ownerId);

    List<Owner> findAllOrderById();

    Optional<Owner> findById(int ownerId);
}
