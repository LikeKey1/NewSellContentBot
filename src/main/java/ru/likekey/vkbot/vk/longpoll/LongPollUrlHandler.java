package ru.likekey.vkbot.vk.longpoll;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LongPollUrlHandler {

    LongPollInfo longPollInfo = new LongPollInfo();
    private String longPollServer = longPollInfo.longPollServer();
    private String longPollKey = longPollInfo.longPollKey();
    private String longPollTs = longPollInfo.longPollTs();

    private String longPollUrl = longPollServer + "?act=a_check&key=" + longPollKey + "&ts=" + longPollTs + "&wait=25";

    public LongPollUrlHandler() throws ClientException, ApiException {
    }

    public String longPollUrlHandler() throws IOException {
        URL obj = new URL(longPollUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
