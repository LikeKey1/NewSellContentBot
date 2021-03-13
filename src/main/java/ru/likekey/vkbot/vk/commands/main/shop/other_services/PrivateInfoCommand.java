package ru.likekey.vkbot.vk.commands.main.shop.other_services;

import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class PrivateInfoCommand extends VkCommand {

    public PrivateInfoCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Вы можете вступить в приватную группу, где будет доступ КО ВСЕМ" +
                " фото и видео без цензуры. Также вы будете получать все новые фото и видео," +
                " вам не придется за них доплачивать. Фото с видео (если покупать по отдельности) " +
                "стоят около 3831Р, а тут вы получите ВСЕ фото и видео за 2000Р, оплатив один раз + " +
                "все новые фото и видео.", MyKeyboards.getBuyPrivateKeyboard());
    }
}
