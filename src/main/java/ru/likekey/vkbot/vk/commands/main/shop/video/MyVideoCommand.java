package ru.likekey.vkbot.vk.commands.main.shop.video;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class MyVideoCommand extends VkCommand {

    public MyVideoCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        List<Video> videos = userService.getUser(getVkId(), true).getVideos();
        if (videos.isEmpty()) {
            sendMessage("Ты еще не купил видео!");
        } else {
            Video video = videos.get(0);
            User user = userService.getUser(getVkId(), false);
            user.setPlace("MY_VIDEO");
            userService.updateUser(user);
            String msg = "Видео #1" +
                    "\nВсего куплено видео: " + videos.size() +
                    "\nСсылка на видео: " + video.getPathReady();
            if (videos.size() == 1) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videos.size(), 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videos.size(), 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
