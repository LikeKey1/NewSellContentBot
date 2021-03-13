package ru.likekey.vkbot.vk.commands.main.balance;

import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;
import ru.likekey.vkbot.vk.payment.QiwiPayment;

public class AddBalanceWithSumCommand extends VkCommand {

    private int sumOfPayment;

    public AddBalanceWithSumCommand(int vkId, int sumOfPayment) {
        super(vkId);
        this.sumOfPayment = sumOfPayment;
    }

    @Override
    public void run() throws Exception {
        if (sumOfPayment <= 0) {
            sendMessage("Введено неверно значение. Вы не можете пополнить баланс на сумму меньшую или равную нулю!");
        } else if (sumOfPayment > 10000) {
            sendMessage("Максимальная сумма для пополнения баланса 10.000 рублей!");
        } else {
            if (userService.getUser(getVkId(), false).getUserPayment().getBillId() == null) {
                String link = QiwiPayment.getInstance().getPaymentUrl(getVkId(), sumOfPayment);
                String msg = "Ссылка для пополнения баланса: \n" + link;
                sendMessage(msg, MyKeyboards.getAddBalanceCheckPaymentKeyboard());
            } else {
                new AddBalanceCommand(getVkId()).run();
            }
        }
    }
}
