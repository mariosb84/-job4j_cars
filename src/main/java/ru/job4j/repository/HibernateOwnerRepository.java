package ru.job4j.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HibernateOwnerRepository implements OwnerRepository {
    private final HibernateCrudRepository hibernateCrudRepository;

    /**
     * Сохранить в базе.
     * @param owner owner.
     * @return owner с id.
     */
    public Owner  create(Owner owner) {
        hibernateCrudRepository.run(session -> session.persist(owner));
        return owner;
    }

    /**
     * Обновить в базе owner.
     * @param owner owner.
     */
    public void update(Owner owner) {
        hibernateCrudRepository.run(session -> session.merge(owner));
    }

    /**
     * Удалить owner по id.
     * @param ownerId ID
     */
    public void delete(int ownerId) {
        hibernateCrudRepository.run(
                "delete from Owner where id = :fId",
                Map.of("fId", ownerId)
        );
    }

    /**
     * Список owners отсортированных по id.
     * @return список owners.
     */
    public List<Owner> findAllOrderById() {
        return hibernateCrudRepository.query("from Owner order by id asc", Owner.class);
    }

    /**
     * Найти owner по ID
     * @return owner.
     */
    public Optional<Owner> findById(int ownerId) {
        return hibernateCrudRepository.optional(
                "from Owner where id = :fId", Owner.class,
                Map.of("fId", ownerId)
        );
    }

}
