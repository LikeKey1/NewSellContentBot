package ru.likekey.vkbot.vk.commands.main.shop.video;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class BuyVideoCommand extends VkCommand {

    public static final int TOTAL_VIDEO = 21;

    public BuyVideoCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), true);
        user.setPlace("BUY_VIDEO");
        userService.updateUser(user);

        Video video = userService.getVideoById(1);


        String msg = "Видео #" + (video.getId()) +
                "\nЦена " + video.getPrice() + "Р " +
                "\nДлительность видео: " + video.getDuration() +
                "\nВсего видео: " + TOTAL_VIDEO +
                "\n\nОписание видео: " + video.getDescription();
        boolean bought = false;
        for (Video video1 : user.getVideos()) {
            if (video1.getId() == video.getId()) {
                bought = true;
            }
        }
        if (bought) {
            sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(TOTAL_VIDEO, 2), video.getPath(),
                    video.getPath2(), video.getPath3());
        } else {
            sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(TOTAL_VIDEO, 1, 2), video.getPath(),
                    video.getPath2(), video.getPath3());
        }
    }
}
