package ru.likekey.vkbot.vk.commands.main.balance;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;
import ru.likekey.vkbot.vk.payment.QiwiPayment;

public class CancelPaymentCommand extends VkCommand {

    public CancelPaymentCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), false);
        String billId = user.getUserPayment().getBillId();
        if (billId != null) {
            if (billId != null) {
                user.getUserPayment().setBillId(null);
                userService.updateUser(user);
                QiwiPayment.getInstance().cancelBill(billId);
                String msg = "Твой счет на оплату отменен!";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
            }
        } else  {
            String msg = "Ты не создал счет для оплаты! Нажми на кнопку 'Пополнить баланс'";
            sendMessage(msg, MyKeyboards.getStartKeyboard());
        }
    }
}
