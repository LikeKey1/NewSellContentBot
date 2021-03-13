package ru.likekey.vkbot.vk.commands.main.balance;

import ru.likekey.vkbot.entity.Payment;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;
import ru.likekey.vkbot.vk.payment.QiwiPayment;

public class AddBalanceCommand extends VkCommand {

    public AddBalanceCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        Payment payment = userService.getUser(getVkId(), false).getUserPayment();

        if (payment.getBillId() == null) {
            String msg = "Нажмите на сумму, на которую хотите пополнить баланс.\n " +
                    "Пополнить можно будет с карты, Qiwi, баланса телефона. \n";

            sendMessage(msg, MyKeyboards.getAddBalanceKeyboard());
        } else {
            String link = QiwiPayment.getInstance().getLinkForPayment(payment.getBillId());
            String amount = QiwiPayment.getInstance().getAmountForPayment(payment.getBillId());
            String msg = "У вас уже создан счет к оплате на сумму " + amount + "Р, по ссылке " + link + " \n\n" +
                    "Чтобы проверить оплату нажмите на кнопку 'Проверить оплату'\n" +
                    "Чтобы отменить счет и создать новый нажмите на кнопку 'Отменить счет'";
            sendMessage(msg, MyKeyboards.getAddBalanceCheckPaymentKeyboard());
        }
    }
}
