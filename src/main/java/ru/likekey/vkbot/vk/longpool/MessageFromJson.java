package ru.likekey.vkbot.vk.longpool;

public class MessageFromJson {

    private int id;
    private int fromId;
    private String text;

    public MessageFromJson(int id, int fromId, String text) {
        this.id = id;
        this.fromId = fromId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public String getText() {
        return text;
    }
}
