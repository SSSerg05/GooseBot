package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

//NameBot: GooseGoitBot
//UserBot: Goose_Goit_Bot
//LinkBot: t.me/Goose_Goit_Bot
//HTTP_API_TOKEN: 6294691614:AAFJ-bk6NIObRBGCAUihNT5xBrz1i_sGRjI

public class Main extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());

        System.out.printf("Start telegramm bot");
    }

    @Override
    public String getBotUsername() {
        return "Goose_Goit_Bot";
    }

    @Override
    public String getBotToken() {
        return "6294691614:AAFJ-bk6NIObRBGCAUihNT5xBrz1i_sGRjI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chartId = getChatId(update);

        SendMessage message = null;
        if (update.hasMessage() && update.getMessage().getText().equals("/start")) {
            message = createMessage("Привіт!");
            message.setChatId(chartId);

            attachButtons(message, Map.of(
                    "Слава Україні", "glory_for_ukraine"
            ));
        }
        sendApiMethodAsync(message);

        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("glory_for_ukraine")) {
                message = createMessage(("Героям Слава!"));
                message.setChatId(chartId);

                attachButtons(message, Map.of(
                    "Слава Нації", "glory_for_nation"
                ));
                sendApiMethodAsync(message);
            }

            if (update.getCallbackQuery().getData().equals("glory_for_nation")) {
                message = createMessage(("Смерть ворогам!"));
                message.setChatId(chartId);
                sendApiMethodAsync(message);
            }
        }

    }

    public Long getChatId(Update update){
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }

        //обробчик кнопок у телеграмботі
        if(update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public SendMessage createMessage(String text){
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");

        return message;
    }

    public void attachButtons(SendMessage message, Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List< InlineKeyboardButton>> keyboard = new ArrayList<>();

        for(String buttonName : buttons.keySet() ) {
            String buttonValue = buttons.get(buttonName);

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}