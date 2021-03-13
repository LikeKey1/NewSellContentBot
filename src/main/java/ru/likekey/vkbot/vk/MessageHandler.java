package ru.likekey.vkbot.vk;

import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.service.UserService;
import ru.likekey.vkbot.service.UserServiceImpl;
import ru.likekey.vkbot.vk.commands.main.*;
import ru.likekey.vkbot.vk.commands.main.balance.AddBalanceCommand;
import ru.likekey.vkbot.vk.commands.main.balance.AddBalanceWithSumCommand;
import ru.likekey.vkbot.vk.commands.main.balance.CancelPaymentCommand;
import ru.likekey.vkbot.vk.commands.main.balance.CheckPaymentCommand;
import ru.likekey.vkbot.vk.commands.main.shop.*;
import ru.likekey.vkbot.vk.commands.main.shop.other_services.BuyPrivateCommand;
import ru.likekey.vkbot.vk.commands.main.shop.other_services.OtherServicesCommand;
import ru.likekey.vkbot.vk.commands.main.shop.other_services.PrivateInfoCommand;
import ru.likekey.vkbot.vk.commands.main.shop.photo.*;
import ru.likekey.vkbot.vk.commands.main.shop.video.*;
import ru.likekey.vkbot.vk.longpoll.MessageFromJson;

public class MessageHandler implements Runnable {

    private static volatile UserService userService = new UserServiceImpl();

    private MessageFromJson message;

    public MessageHandler(MessageFromJson message) {
        this.message = message;
    }

    private static void parseMessage(MessageFromJson message) throws Exception {
        int vkId = message.getFromId();
        String[] args = message.getText().split(" ");
        String command = args[0].toLowerCase();

        userService.checkUserInDB(vkId);
        User user = userService.getUser(vkId, false);

        String place = user.getPlace();

        switch (command) {
            case "начать":
                new StartCommand(vkId).run();
                break;

            case "профиль":
                new ProfileCommand(vkId).run();
                break;

            case "баланс":
                new BalanceCommand(vkId).run();
                break;

            case "магазин":
                new ShopCommand(vkId).run();
                break;

            case "задания":
                new TasksCommand(vkId).run();
                break;

            case "пополнить":
                new AddBalanceCommand(vkId).run();
                break;

            case "на":
                if (args.length > 1) {
                    new AddBalanceWithSumCommand(vkId, Integer.parseInt(args[1].substring(0, args[1].length() - 1))).run();
                }
                break;

            case "проверить":
                new CheckPaymentCommand(vkId).run();
                break;

            case "отменить":
                new CancelPaymentCommand(vkId).run();
                break;

            case "купить":
                if (args.length > 1) {
                    switch (args[1].toLowerCase()) {
                        case "фото":
                            new BuyPhotoCommand(vkId).run();
                            break;

                        case "видео":
                            new BuyVideoCommand(vkId).run();
                            break;

                        case "приват":
                            new BuyPrivateCommand(vkId).run();
                            break;
                    }
                }
                break;

            case "мои":
                if (args.length > 1) {
                    switch (args[1].toLowerCase()) {
                        case "фото":
                            new MyPhotoCommand(vkId).run();
                            break;

                        case "видео":
                            new MyVideoCommand(vkId).run();
                            break;
                    }
                }
                break;

            case "другие":
                new OtherServicesCommand(vkId).run();
                break;

            case "приватная":
                new PrivateInfoCommand(vkId).run();
                break;

            case "предыдущее":
            case "следующее":
                if (args.length > 1) {
                    switch (place) {
                        case "BUY_PHOTO":
                            new TheNextBuyPhotoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;
                        case "BUY_VIDEO":
                            new TheNextBuyVideoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;

                        case "MY_PHOTO":
                            new TheNextMyPhotoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;

                        case "MY_VIDEO":
                            new TheNextMyVideoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;
                    }
                }
                break;

            case "приобрести":
                if (args.length > 1) {
                    switch (place) {
                        case "BUY_PHOTO":
                            new BuyNewPhotoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;
                        case "BUY_VIDEO":
                            new BuyNewVideoCommand(vkId, Integer.parseInt(args[1])).run();
                            break;
                    }
                }
                break;

            case "выйти":
            case "назад":
                if (!place.equals("MAIN")) {
                    new ExitFromShopCommand(vkId).run();
                } else {
                    new StartCommand(vkId).run();
                }
                break;
        }
    }

    @Override
    public void run() {
        try {
            parseMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
