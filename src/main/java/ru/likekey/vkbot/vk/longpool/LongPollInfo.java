package ru.likekey.vkbot.vk.longpool;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;
import ru.likekey.vkbot.vk.Connection;

import java.io.IOException;

public class LongPollInfo {

    Connection connection = new Connection();
    private VkApiClient vk = connection.vk();
    private GroupActor actor = connection.actor();
    private int groupdId = connection.groupId();

    GetLongPollServerResponse longPollInfo = vk.groups().getLongPollServer(actor, groupdId).execute();

    public LongPollInfo() throws ClientException, ApiException {
    }

    public String longPollServer() { return longPollInfo.getServer(); }
    public String longPollKey() { return  longPollInfo.getKey(); }
    public String longPollTs() { return longPollInfo.getTs(); }

    public void longPollUpdate() throws ClientException, ApiException, IOException {
        LongPollJsonParser longPollJsonParser = new LongPollJsonParser();
        longPollJsonParser.startParsing();
    }
}
