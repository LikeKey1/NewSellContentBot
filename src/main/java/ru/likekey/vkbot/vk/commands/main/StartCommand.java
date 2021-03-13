package ru.likekey.vkbot.vk.commands.main;

import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class StartCommand extends VkCommand {

    public StartCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        userService.checkUserInDB(getVkId());
        String msg = "Привет, в этом боте ты можешь получить мои фото и видео ;)\n\n " +
                "Используй кнопки, чтобы передвигаться по меню бота.";
        sendMessage(msg, MyKeyboards.getStartKeyboard());
    }
}
