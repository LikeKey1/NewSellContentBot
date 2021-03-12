package ru.likekey.vkbot.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.likekey.vkbot.entity.Payment;
import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;

import java.util.List;

public class PhotoDAOImpl implements PhotoDAO {

    private static SessionFactory sessionFactory;

    public PhotoDAOImpl() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Photo.class)
                    .addAnnotatedClass(Video.class)
                    .buildSessionFactory();
        }
    }

    @Override
    public Photo getPhotoById(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Photo> photo;
        try {
            session.beginTransaction();
            photo = session.createQuery("from Photo where id = " + id).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        return photo.get(0);
    }
}
