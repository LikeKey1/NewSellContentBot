package ru.likekey.vkbot.vk.commands.main.shop.video;

import ru.likekey.vkbot.entity.Video;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class TheNextBuyVideoCommand extends VkCommand {

    private int videoId;

    public TheNextBuyVideoCommand(int vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        Video video;
        try {
            video = userService.getVideoById(videoId);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            sendMessage("Такого видео нет!");
            return;
        }
        List<Video> boughtVideos = userService.getUser(getVkId(), true).getVideos();
        boolean checker = true;
        for (Video video1 : boughtVideos) {
            if (video.getId() == video1.getId()) checker = false;
        }
        String msg = "Видео #" + (video.getId()) +
                "\nЦена " + video.getPrice() + "Р " +
                "\nДлительность видео: " + video.getDuration() +
                "\nВсего видео: " + BuyVideoCommand.TOTAL_VIDEO +
                "\n\nОписание видео: " + video.getDescription();
        if (checker) {
            if (videoId == BuyVideoCommand.TOTAL_VIDEO) {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(videoId-1, videoId, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(BuyVideoCommand.TOTAL_VIDEO, videoId, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(videoId-1, videoId, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        } else {

            if (videoId == BuyVideoCommand.TOTAL_VIDEO) {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(videoId-1, 1),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else if (videoId == 1) {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.TOTAL_VIDEO, 2),
                        video.getPath(), video.getPath2(), video.getPath3());
            } else {
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(videoId-1, videoId+1),
                        video.getPath(), video.getPath2(), video.getPath3());
            }
        }
    }
}
