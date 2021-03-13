package ru.likekey.vkbot.vk.commands.main.shop.photo;

import com.vk.api.sdk.objects.messages.Keyboard;
import ru.likekey.vkbot.entity.Photo;
import ru.likekey.vkbot.entity.User;
import ru.likekey.vkbot.vk.MyKeyboards;
import ru.likekey.vkbot.vk.commands.VkCommand;

import java.util.List;

public class BuyNewPhotoCommand extends VkCommand {

    private int photoId;

    public BuyNewPhotoCommand(int vkId, int photoId) {
        super(vkId);
        this.photoId = photoId;
    }

    @Override
    public void run() throws Exception {
        User user = userService.getUser(getVkId(), true);
        Photo photo = userService.getPhotoById(photoId+3);
        List<Photo> boughtPhotos = user.getPhotos();
        boolean checker = true;
        for (Photo boughtPhoto : boughtPhotos) {
            if (boughtPhoto.getId() == photo.getId()) checker = false;
        }
        Keyboard keyboard;

        if (photoId == 1) {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.TOTAL_PHOTO, 2);
        } else if (photoId == BuyPhotoCommand.TOTAL_PHOTO) {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(BuyPhotoCommand.TOTAL_PHOTO-1, 1);
        } else {
            keyboard = MyKeyboards.getBoughtPhotoKeyboard(photoId-1, photoId+1);
        }
        if (checker) {
            int balance = user.getBalance();
            int price = photo.getPrice();
            if (balance >= price) {
                user.addPhotoToUser(photo);
                user.setBalance(balance - price);
                userService.updateUser(user);

                String msg = "Фото #" + photoId +
                        "\nЦена " + photo.getPrice() + "Р " +
                        "\nВсего фото: " + BuyPhotoCommand.TOTAL_PHOTO;
                sendMessage(msg,
                        keyboard,
                        photo.getPathReady());
            } else {
                sendMessage("Тебе не хватает " + (price - balance) + "Р :( Чтобы пополнить баланс вернись" +
                        " в главное меню и нажми на 'Пополнить баланс'", keyboard, photo.getPath());
            }
        } else {
            sendMessage("Ошибка! Ты уже купил это фото..");
        }
    }
}
