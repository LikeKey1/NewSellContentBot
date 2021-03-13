package ru.likekey.vkbot.service;

import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;

import java.util.List;

public interface UserService {

    // UserDAO
    public void saveUser(User user);
    public void updateUser(User user);
    public User getUser(int vkId, boolean withFullInit);
    public void checkUserInDB(int vkId);

    // PhotoDAO
    public Photo getPhotoById(int id);

    // VideoDAO
    public Video getVideoById(int id);
}
