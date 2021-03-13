package ru.likekey.vkbot.vk.commands.main;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class BalanceCommand extends VkCommand {

    public BalanceCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), false);
        String msg = "\uD83D\uDCB0Ваш баланс: " + user.getBalance() + "₽";
        sendMessage(msg, MyKeyboards.getInlineFromProfileKeyboard());
    }
}
