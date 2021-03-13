package ru.likekey.vkbot.vk.commands.main.shop.other_services;

import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class OtherServicesCommand extends VkCommand {

    public OtherServicesCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Выберите интересующую вас услугу", MyKeyboards.getOtherServicesKeyBoard());
    }
}
