package ru.likekey.vkbot.vk.longpoll;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import ru.likekey.vkbot.vk.MessageHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LongPollJsonParser {

    LongPollUrlHandler longPollUrlHandler = new LongPollUrlHandler();
    LongPollInfo longPollInfo = new LongPollInfo();
    private String URL = longPollUrlHandler.longPollUrlHandler();


    public LongPollJsonParser() throws ClientException, ApiException, IOException {
    }

    private static Float getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss.SS");
        return Float.parseFloat(simpleDateFormat.format(new Date().getTime()));
    }

    public void startParsing() throws ClientException, ApiException, IOException {
        while (true) {
            JsonElement jsonParser = new JsonParser().parse(URL);
            JsonObject json = jsonParser.getAsJsonObject();
            try {
//                Float start = getTime();
                json = json.getAsJsonArray("updates").get(0).getAsJsonObject();
                String type = json.get("type").getAsString();
                json = json.getAsJsonObject("object");
                if (type.equals("message_new")) {
                    MessageHandler messageHandler = new MessageHandler(new MessageFromJson(
                            json.get("id").getAsInt(),
                            json.get("user_id").getAsInt(),
                            json.get("body").getAsString()
                    ));
                    new Thread(messageHandler).start();
                } else if (type.equals("message_event")) {
                    MessageHandler messageHandler = new MessageHandler(new MessageFromJson(
                            json.get("conversation_message_id").getAsInt(),
                            json.get("user_id").getAsInt(),
                            json.getAsJsonObject("payload").get("button").getAsString()
                    ));
                    new Thread(messageHandler).start();
                }

//                float time = getTime() - start;
//                if (time < 0.00001) System.err.println("Сообщение обработано за: < 0.00001 сек.");
//                else { System.err.println("Сообщение обработано за: " + time + " сек."); }
            } catch (IndexOutOfBoundsException e) {
            } catch (NullPointerException e) {
            } catch (Exception e) {
            }
            longPollInfo.longPollUpdate();
        }
    }
}
