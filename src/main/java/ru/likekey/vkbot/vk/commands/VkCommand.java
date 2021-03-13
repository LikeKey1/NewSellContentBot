package ru.likekey.vkbot.vk.commands;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;

import ru.likekey.vkbot.service.UserService;
import ru.likekey.vkbot.service.UserServiceImpl;
import ru.likekey.vkbot.vk.Connection;

import java.util.Random;

public abstract class VkCommand {

    protected UserService userService = new UserServiceImpl();
    private static Connection connection;

    private int vkId;

    public VkCommand(int vkId) {
        this.vkId = vkId;
        if (connection == null) connection = new Connection();
    }

    public abstract void run() throws Exception;

    public int getVkId() { return vkId; }

    public void sendMessage(String msg) throws ClientException, ApiException {
        connection.vk().messages().send(connection.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .peerId(getVkId()).execute();
    }

    public void sendMessage(String msg, Keyboard keyboard) throws ClientException, ApiException {
        connection.vk().messages().send(connection.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .keyboard(keyboard)
                .peerId(getVkId()).execute();
    }

    public void sendMessage(String msg, Keyboard keyboard, String attachment) throws ClientException, ApiException {
        connection.vk().messages().send(connection.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .keyboard(keyboard)
                .attachment(attachment)
                .peerId(getVkId()).execute();
    }

    public void sendMessage(String msg, String attachment) throws ClientException, ApiException {
        connection.vk().messages().send(connection.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .attachment(attachment)
                .peerId(getVkId()).execute();
    }

    public void sendMessage(String msg, Keyboard keyboard, String attachment1,
                            String attachment2, String attachment3) throws ClientException, ApiException {
        connection.vk().messages().send(connection.actor())
                .randomId(new Random().nextInt(10000))
                .message(msg)
                .keyboard(keyboard)
                .attachment(attachment1 + "," + attachment2 + "," + attachment3)
                .peerId(getVkId()).execute();
    }
}
