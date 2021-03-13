package ru.likekey.vkbot;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.likekey.vkbot.vk.longpoll.LongPollJsonParser;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws ClientException, ApiException, IOException {
        LongPollJsonParser longPollJsonParser = new LongPollJsonParser();
        longPollJsonParser.startParsing();
    }
}
