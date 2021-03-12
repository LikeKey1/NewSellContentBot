package ru.likekey.vkbot.vk;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import ru.likekey.vkbot.Application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connection {

    private static String token;
    private static int groupId;
    private static VkApiClient vk;
    private static GroupActor actor;

    public Connection() {
        if (token == null) {
            Properties properties = loadConfiguration();
            token = properties.getProperty("vk.group.token");
            groupId = Integer.parseInt(properties.getProperty("vk.group.id"));
            vk = new VkApiClient(HttpTransportClient.getInstance());
            actor = new GroupActor(groupId, token);
        }
    }

    public int groupId() { return groupId; }
    public VkApiClient vk() { return vk; }
    public GroupActor actor() { return actor; }

    private static Properties loadConfiguration() {
        Properties properties = new Properties();
        try (InputStream is = Application.class.getResourceAsStream("/config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return properties;
    }
}
