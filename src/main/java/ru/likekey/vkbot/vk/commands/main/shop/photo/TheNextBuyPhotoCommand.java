package ru.likekey.vkbot.vk.commands.main.shop.photo;

import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;
import ru.likekey.vkbot.vk.commands.main.shop.photo.BuyPhotoCommand;

import java.util.List;


public class TheNextBuyPhotoCommand extends VkCommand {

    private int photoId;

    public TheNextBuyPhotoCommand(int vkId, int photoNum) {
        super(vkId);
        this.photoId = photoNum;
    }

    @Override
    public void run() throws Exception {
        Photo photo;
        try {
            photo = userService.getPhotoById(photoId + 3);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            sendMessage("Такого фото нет!");
            return;
        }
        List<Photo> boughtPhotos = userService.getUser(getVkId(), true).getPhotos();
        boolean checker = true;
        for (Photo boughtPhoto : boughtPhotos) {
            if (boughtPhoto.getId() == photo.getId()) checker = false;
        }
        if (checker) {
            if (photoId == BuyPhotoCommand.TOTAL_PHOTO) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(photoId-1, photoId, 1), photo.getPath());
            } else if (photoId == 1) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(BuyPhotoCommand.TOTAL_PHOTO, photoId, 2), photo.getPath());
            } else {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getNotBoughtPhotoKeyboard(photoId-1, photoId, photoId+1), photo.getPath());
            }
        } else {
            if (photoId == BuyPhotoCommand.TOTAL_PHOTO) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(photoId-1, 1), photo.getPathReady());
            } else if (photoId == 1) {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.TOTAL_PHOTO, 2), photo.getPathReady());
            } else {
                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg, MyKeyboards.getBoughtPhotoKeyboard(photoId-1, photoId+1), photo.getPathReady());
            }
        }
    }
}
