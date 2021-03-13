package ru.likekey.vkbot.vk.commands.main.shop.photo;

import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class BuyPhotoCommand extends VkCommand {

    public static final int TOTAL_PHOTO = 261;

    public BuyPhotoCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), true);
        user.setPlace("BUY_PHOTO");
        userService.updateUser(user);

        Photo photo = userService.getPhotoById(4);


        String msg = "Фото #" + (photo.getId()-3) +
                "\nЦена " + photo.getPrice() + "Р " +
                "\nВсего фото: " + TOTAL_PHOTO;
        boolean bought = false;
        for (Photo photo1 : user.getPhotos()) {
            if (photo1.getId() == photo.getId()) {
                bought = true;
            }
        }
        if (bought) {
            sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(TOTAL_PHOTO, 2), photo.getPathReady());
        } else {
            sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(TOTAL_PHOTO, 1, 2), photo.getPath());
        }
    }
}
