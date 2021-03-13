package ru.likekey.vkbot.vk.commands.main;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.hibernate.Hibernate;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.Connection;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

public class ProfileCommand extends VkCommand {

    public ProfileCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), true);
        Hibernate.initialize(user);
        String msg = "@id" + getVkId() + " (" + getFirstName() + "), Ваш профиль:\n" +
                "\uD83D\uDD0E ID: " + user.getId() +
                "\n\uD83D\uDCB0 Баланс: " + user.getBalance() + "₽" +
                "\n\uD83D\uDCF7 Куплено фото: " + user.getPhotos().size() +
                "\n\uD83C\uDFA5 Куплено видео: " + user.getVideos().size();

        sendMessage(msg, MyKeyboards.getInlineFromProfileKeyboard());
    }

    private String getFirstName() {
        Connection connection = new Connection();
        try {
            return connection.vk().users().get(connection.actor())
                    .userIds(Integer.toString(getVkId())).execute().get(0).getFirstName();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
