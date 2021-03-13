package ru.likekey.vkbot.vk.commands.main.shop.video;

import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.entity.Video;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class BuyNewVideoCommand extends VkCommand {

    private int videoId;

    public BuyNewVideoCommand(int vkId, int videoId) {
        super(vkId);
        this.videoId = videoId;
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), true);
        Video video = userService.getVideoById(videoId);
        List<Video> boughtVideos = user.getVideos();
        boolean checker = true;
        for (Video boughtPhoto : boughtVideos) {
            if (boughtPhoto.getId() == video.getId()) checker = false;
        }
        Keyboard keyboard;

        if (videoId == 1) {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.TOTAL_VIDEO, 2);
        } else if (videoId == BuyVideoCommand.TOTAL_VIDEO) {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyVideoCommand.TOTAL_VIDEO-1, 1);
        } else {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(videoId-1, videoId+1);
        }
        if (checker) {
            int balance = user.getBalance();
            int price = video.getPrice();
            if (balance >= price) {
                user.addVideoToUser(video);
                user.setBalance(balance - price);
                userService.updateUser(user);

                sendMessage("Спасибо за покупку:)\n" +
                                "Ссылка на видео: " + video.getPathReady() +
                                "\nВесь купленный контент доступен в разделах " +
                                "'Мои фото/Мои видео'",
                        keyboard);
            } else {
                sendMessage("Тебе не хватает " + (price - balance) + "Р :( Чтобы пополнить баланс вернись" +
                        " в главное меню и нажми на 'Пополнить баланс'", keyboard, video.getPath(), video.getPath2(), video.getPath3());
            }
        } else {
            user.setPlace("MAIN");
            userService.updateUser(user);
            sendMessage("Ошибка! Ты уже купил это видео..", MyKeyboards.getStartKeyboard());
        }
    }
}
