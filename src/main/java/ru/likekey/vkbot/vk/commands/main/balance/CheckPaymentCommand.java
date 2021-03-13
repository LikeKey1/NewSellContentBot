package ru.likekey.vkbot.vk.commands.main.balance;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;
import ru.likekey.vkbot.vk.payment.QiwiPayment;

public class CheckPaymentCommand extends VkCommand {

    public CheckPaymentCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), false);
        String billId = user.getUserPayment().getBillId();
        QiwiPayment qiwiPayment =  QiwiPayment.getInstance();
        String result = qiwiPayment.checkPayment(billId);
        if (billId != null) {
            if (result.equals("PAID")) {

                int amount = Integer.parseInt(qiwiPayment.getAmountForPayment(billId));

                user.setBalance(user.getBalance() + amount);
                user.getUserPayment().setBillId(null);
                userService.updateUser(user);

                String msg = "Ты только что пополнил баланс на " + amount + " рублей!";
                sendMessage(msg, MyKeyboards.getStartKeyboard());
            } else if (result.equals("EXPIRED")) {
                String msg = "Время жизни счета истекло. Происходит удаление счета, подождите..";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
                user.getUserPayment().setBillId(null);
                userService.updateUser(user);
            } else if (result.equals("REJECTED")) {
                String msg = "Счет отклонен. Происходит удаление счета, подождите..";
                sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
                user.getUserPayment().setBillId(null);
                userService.updateUser(user);
            } else if (result.equals("WAITING")) {
                String msg = "Счет не оплачен.";
                sendMessage(msg);
            } else if (result.equals("MISTAKE")) {
                String msg = "Произошла какая-то ошибка на стороне Qiwi, возможно тех. работы. Повторите запрос " +
                        "на пополнение баланса чуть позже. Вы можете посмотреть статус работы Qiwi кошелька по ссылке: " +
                        "status.qiwi.com";
                sendMessage(msg);
            }
        } else  {
            String msg = "Ты не создал счет для оплаты! Нажми на кнопку 'Пополнить баланс'";
            sendMessage(msg, MyKeyboards.getStartKeyboard());
        }
    }
}
