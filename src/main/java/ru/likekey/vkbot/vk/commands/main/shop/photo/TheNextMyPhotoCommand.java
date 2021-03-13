package ru.likekey.vkbot.vk.commands.main.shop.photo;

import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class TheNextMyPhotoCommand extends VkCommand {

    private int photoId;

    public TheNextMyPhotoCommand(int vkId, int photoId) {
        super(vkId);
        this.photoId = photoId;
    }

    @Override
    public void run() throws Exception {
        List<Photo> photos = userService.getUser(getVkId(), true).getPhotos();
        int maxPhotoId = photos.size();
        if (photoId < 1 || photoId > maxPhotoId) {
            sendMessage("Такого фото нет!");
        } else  {
            String msg = "Фото #" + photoId +
                    "\nВсего куплено фото: " + maxPhotoId;
            if (photoId == maxPhotoId) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(photoId-1, 1), photos.get(photoId-1).getPathReady());
            } else if (photoId == 1) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(maxPhotoId, 2), photos.get(photoId-1).getPathReady());
            } else {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(photoId-1, photoId+1), photos.get(photoId-1).getPathReady());
            }
        }
    }
}
