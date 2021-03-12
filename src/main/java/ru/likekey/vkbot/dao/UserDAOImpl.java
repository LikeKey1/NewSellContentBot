package ru.likekey.vkbot.dao;

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
    public User getUser(int vkId) {
        Session session = sessionFactory.getCurrentSession();
        List<User> user;
        try {
            session.beginTransaction();
            String query = "from User where vkId = " + vkId;
            user = session.createQuery(query).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        if (user.size() == 0) return null;
        return user.get(0);
    }

    @Override
    public void checkUserInDB(int vkId) {
        User user = getUser(vkId);
        if (user == null) {
            User newUser = new User(vkId, 5, "MAIN");
            Payment payment = new Payment();
            newUser.setUserPayment(payment);
            saveUser(newUser);
        }
    }
}
