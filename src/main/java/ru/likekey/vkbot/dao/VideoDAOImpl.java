package ru.likekey.vkbot.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    private static SessionFactory sessionFactory;

    public VideoDAOImpl() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Video.class)
                    .buildSessionFactory();
        }
    }

    @Override
    public Video getVideoById(int id) {
        Session session = sessionFactory.getCurrentSession();
        List<Video> videos;
        try {
            session.beginTransaction();
            videos = session.createQuery("from Video where id = " + id).getResultList();
        } finally {
            session.getTransaction().commit();
        }
        return videos.get(0);
    }
}
