package ru.likekey.vkbot.service;

import ru.likekey.vkbot.dao.*;
import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private PhotoDAO photoDAO;
    private VideoDAO videoDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        photoDAO = new PhotoDAOImpl();
        videoDAO = new VideoDAOImpl();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public User getUser(int vkId, boolean withFullInit) {
        return userDAO.getUser(vkId, withFullInit);
    }

    @Override
    public void checkUserInDB(int vkId) {
        userDAO.checkUserInDB(vkId);
    }

    @Override
    public Photo getPhotoById(int id) {
        return photoDAO.getPhotoById(id);
    }

    @Override
    public Video getVideoById(int id) {
        return videoDAO.getVideoById(id);
    }
}
