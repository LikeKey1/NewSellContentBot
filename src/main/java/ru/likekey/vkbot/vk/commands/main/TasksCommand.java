package ru.likekey.vkbot.vk.commands.main;

import ru.likekey.vkbot.vk.commands.VkCommand;

public class TasksCommand extends VkCommand {

    public TasksCommand(int vkId) {
        super(vkId);
    }

    @Override
    public void run() throws Exception {
        sendMessage("Раздел в разработке");
    }
}
