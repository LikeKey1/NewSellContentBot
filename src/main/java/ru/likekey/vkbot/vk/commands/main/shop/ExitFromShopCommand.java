package ru.likekey.vkbot.vk.commands.main.shop;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class ExitFromShopCommand extends VkCommand {

    public ExitFromShopCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), false);
        user.setPlace("MAIN");
        userService.updateUser(user);
        sendMessage("Вы вернулись в главное меню!", MyKeyboards.getStartKeyboard());
    }
}
