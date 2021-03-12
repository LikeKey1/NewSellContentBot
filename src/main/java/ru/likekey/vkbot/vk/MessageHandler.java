package ru.likekey.vkbot.vk;

import ru.likekey.vkbot.service.UserService;
import ru.likekey.vkbot.service.UserServiceImpl;
import ru.likekey.vkbot.vk.longpool.MessageFromJson;

public class MessageHandler {

    private static final UserService userService = new UserServiceImpl();

    public static void parseMessage(MessageFromJson message) {
        System.out.println("Проверяем text");
        if (message.getText().equals("привет")) {
            System.out.println("\n\n\nПришло сообщение привет\n\n\n");
        } else {
            System.out.println("\nПришло какое то сообщение: " + message.getText() + "\n");
        }
    }
}
