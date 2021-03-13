package ru.likekey.vkbot.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.likekey.vkbot.entity.Payment;
import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static SessionFactory sessionFactory;

    public UserDAOImpl() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Video.class)
                    .addAnnotatedClass(Photo.class)
                    .buildSessionFactory();
        }
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(user);
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public User getUser(int vkId, boolean withFullInit) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users;
        User user;
        try {
            session.beginTransaction();
            String query = "from User where vkId = " + vkId;
            users = session.createQuery(query).getResultList();
            if (users.isEmpty()) return null;
            user = users.get(0);
            if (withFullInit) {
                Hibernate.initialize(user.getPhotos());
                Hibernate.initialize(user.getVideos());
            }
        } finally {
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public void checkUserInDB(int vkId) {
        User user = getUser(vkId, false);
        if (user == null) {
            User newUser = new User(vkId, 5, "MAIN");
            Payment payment = new Payment();
            newUser.setUserPayment(payment);
            saveUser(newUser);
        }
    }
}
