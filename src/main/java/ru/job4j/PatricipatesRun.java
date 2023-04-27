package ru.job4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Post;
import ru.job4j.model.PriceHistory;
import ru.job4j.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class PatricipatesRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry2 = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf2 = new MetadataSources(registry2).buildMetadata().buildSessionFactory();
            var user = new User();
            user.setLogin("ADMIN_100");
            user.setPassword("ADMIN_100");
            create(user, sf2);
            var post = new Post();
            post.setDescription("Post_100");
            post.setCreated(LocalDateTime.now());
            post.setPriceHistories(List.of(
                    new PriceHistory(0, 100, 150, LocalDateTime.now()),
                    new PriceHistory(0, 200, 250, LocalDateTime.now())
            ));
            post.setUser(user);
            post.setDescription("Learning Hibernate");
            post.setParticipates(List.of(user));
            create(post, sf2);
            sf2.openSession()
                    .createQuery("from Post where id = :fId", Post.class)
                    .setParameter("fId", post.getId())
                    .getSingleResult()
                    .getParticipates()
                    .forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry2);
        }
    }

    public static <T> T create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.persist(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    public static void update(Post post, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(post);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Post post = new Post();
        post.setId(id);
        session.delete(post);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Post> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from ru.job4j.model.Post").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Post findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Post result = session.get(Post.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}