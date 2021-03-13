package ru.likekey.vkbot.vk.commands.main;

import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class ShopCommand extends VkCommand {

    public ShopCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Выберите нужный раздел", MyKeyboards.getShopKeyboard());
    }
}
