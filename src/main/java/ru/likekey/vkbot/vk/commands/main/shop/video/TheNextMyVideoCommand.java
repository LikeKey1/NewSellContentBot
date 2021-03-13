package ru.likekey.vkbot.vk.commands.main.shop.video;

import ru.likekey.vkbot.entity.Video;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class TheNextMyVideoCommand extends VkCommand {

    private int videoId;

    public TheNextMyVideoCommand(int vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        List<Video> videos = userService.getUser(getVkId(), true).getVideos();
        int maxVideoId = videos.size();
        if (videoId < 1 || videoId > maxVideoId) {
            sendMessage("Такого видео нет!");
        } else  {
            String msg = "Видео #" + videoId +
                    "\nВсего куплено видео: " + maxVideoId +
                    "\nСсылка на видео: " + videos.get(videoId-1).getPathReady();
            Video video = videos.get(videoId-1);
            if (videoId == maxVideoId) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId-1, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getMyPhotoKeyboard(videoId-1, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
