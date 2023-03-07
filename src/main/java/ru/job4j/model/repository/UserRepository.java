package ru.job4j.model.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
           session.beginTransaction();
            session.createQuery(
                    "UPDATE User SET login = :fLogin, password = : fPassword WHERE id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
           session.getTransaction().rollback();
        }
        session.close();
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                    "DELETE User WHERE id = :fId")
                    .setParameter("fId", userId)
                    .executeUpdate();
          session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery(
                "from User order by id");
        ArrayList<User> result = new ArrayList<User>(query.list());
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        org.hibernate.query.Query<User> query = session.createQuery(
                "from User as u where u.id = :fId", User.class);
        query.setParameter("fId", id);
        Optional<User> result = query.uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery(
                "from User as u where u.login LIKE : key ", User.class);
        query.setParameter("key", "%" + key + "%");
        ArrayList<User> result = new ArrayList<User>(query.list());
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        session.beginTransaction();
        org.hibernate.query.Query<User> query = session.createQuery(
                "from User as u where u.login = :fLogin", User.class);
        query.setParameter("fLogin", login);
        Optional<User> result = query.uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
