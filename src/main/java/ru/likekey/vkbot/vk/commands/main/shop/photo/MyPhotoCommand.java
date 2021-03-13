package ru.likekey.vkbot.vk.commands.main.shop.photo;

import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class MyPhotoCommand extends VkCommand {

    public MyPhotoCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        List<Photo> photos = userService.getUser(getVkId(), true).getPhotos();
        if (photos.isEmpty()) {
            sendMessage("Ты еще не купил фото!");
        } else {
            Photo photo = photos.get(0);
            User user = userService.getUser(getVkId(), false);
            user.setPlace("MY_PHOTO");
            userService.updateUser(user);
            String msg = "Фото #1" +
                    "\nВсего куплено фото: " + photos.size();
            sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(photos.size(), 2), photo.getPathReady());
        }
    }
}
