package ru.likekey.vkbot.vk.commands.main.shop.other_services;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class BuyPrivateCommand extends VkCommand {

    public BuyPrivateCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), false);
        int balance = user.getBalance();

        if (balance >= 2000) {
            user.setBalance(balance-2000);
            userService.updateUser(user);
            sendMessage("Спасибо за покупку:) А теперь отправь заявку в приват vk.com/club202272223," +
                    " тебя примут в ближайшее время", MyKeyboards.getStartKeyboard());
        } else {
            sendMessage("Тебе не хватает " + (2000 - balance) + "Р, пополни баланс", MyKeyboards.getAddBalanceKeyboard());
        }
    }
}
