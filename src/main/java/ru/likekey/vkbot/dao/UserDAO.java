package ru.likekey.vkbot.dao;

import ru.likekey.vkbot.entity.User;

public interface UserDAO {
    public void saveUser(User user);
    public void updateUser(User user);
    public User getUser(int vkId);
    public void checkUserInDB(int vkId);
}
