package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.File;

//NameBot: GooseGoitBot
//UserBot: Goose_Goit_Bot
//LinkBot: t.me/Goose_Goit_Bot
//HTTP_API_TOKEN: 6294691614:AAFJ-bk6NIObRBGCAUihNT5xBrz1i_sGRjI

public class Main extends TelegramLongPollingBot {
    private Map<Long, Integer> levels = new HashMap<>();

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
            // send image for level 1
            sendImage("level-1", chartId);

            // send message for level 1
            message = createMessage("Ґа-ґа-ґа!\n" +
                    "Вітаємо у боті біолабораторії «Батько наш Бандера».\n" +
                    "\n" +
                    "Ти отримуєш гусака №71\n" +
                    "\n" +
                    "Цей бот ми створили для того, щоб твій гусак прокачався з рівня звичайної свійської худоби до рівня біологічної зброї, здатної нищити ворога. \n" +
                    "\n" +
                    "Щоб звичайний гусак перетворився на бандерогусака, тобі необхідно:\n" +
                    "✔ виконувати завдання\n" +
                    "✔ переходити на наступні рівні\n" +
                    "✔ заробити достатню кількість монет, щоб придбати Джавеліну і зробити свєрхтра-та-та.\n" +
                    "\n" +
                    "*Гусак звичайний.* Стартовий рівень.\n" +
                    "Бонус: 5 монет.\n" +
                    "Обери завдання, щоб перейти на наступний рівень");
            message.setChatId(chartId);

            List<String> buttons = Arrays.asList(
                    "Сплести маскувальну сітку (+15 монет)",
                    "Зібрати кошти патріотичними піснями (+15 монет)",
                    "Вступити в Міністерство Мемів України (+15 монет)",
                    "Запустити волонтерську акцію (+15 монет)",
                    "Вступити до лав тероборони (+15 монет))"
            );
            buttons = getRandom3(buttons);

            // send Buttons for level 1
            attachButtons(message, Map.of(
                    buttons.get(0), "level_1_task",
                    buttons.get(1), "level_1_task",
                    buttons.get(2), "level_1_task"
            ));
            sendApiMethodAsync(message);
        }

        if (update.hasCallbackQuery()) {
            // End Level 1
            if (update.getCallbackQuery().getData().equals("level_1_task") && getLevel(chartId) == 1) {
                // increase level
                setLevel(chartId, 2);

                // send image for level 2
                sendImage("level-2", chartId);

                // send message for level 2
                message = createMessage("*Вітаємо на другому рівні! Твій гусак - біогусак.*\n" +
                        "Баланс: 20 монет.\n" +
                        "Обери завдання, щоб перейти на наступний рівень\n");
                message.setChatId(chartId);

                List<String> buttons = Arrays.asList(
                        "Зібрати комарів для нової біологічної зброї (+15 монет)",
                        "Пройти курс молодого бійця (+15 монет)",
                        "Задонатити на ЗСУ (+15 монет)",
                        "Збити дрона банкою огірків (+15 монет)",
                        "Зробити запаси коктейлів Молотова (+15 монет)"
                );
                buttons = getRandom3(buttons);

                // send buttons for level 2
                attachButtons(message, Map.of(
                        buttons.get(0), "level_2_task",
                        buttons.get(1), "level_2_task",
                        buttons.get(2), "level_2_task"
                ));
                sendApiMethodAsync(message);
            }

            // End Level 2
            if (update.getCallbackQuery().getData().equals("level_2_task") && getLevel(chartId) == 2) {
                // increase level
                setLevel(chartId, 3);

                // send image for level 3
                sendImage("level-3", chartId);

                // send message for level 3
                message = createMessage("*Вітаємо на третьому рівні! Твій гусак - бандеростажер.*\n" +
                        "Баланс: 35 монет.\n" +
                        "Обери завдання, щоб перейти на наступний рівень\n");
                message.setChatId(chartId);

                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );
                buttons = getRandom3(buttons);

                // send buttons for level 3
                attachButtons(message, Map.of(
                        buttons.get(0), "level_3_task",
                        buttons.get(1), "level_3_task",
                        buttons.get(2), "level_3_task"
                ));
                sendApiMethodAsync(message);
            }

            // End Level 3
            if (update.getCallbackQuery().getData().equals("level_3_task") && getLevel(chartId) == 3) {
                // increase level
                setLevel(chartId, 4);

                // send image for level 4
                sendImage("level-4", chartId);

                // send message for level 4
                message = createMessage("*Вітаємо на останньому рівні!\n" +
                        "Твій гусак - готова біологічна зброя - бандерогусак.*\n" +
                        "Баланс: 50 монет. \n" +
                        "Тепер ти можеш придбати Джавелін і глушити чмонь\n");
                message.setChatId(chartId);

                List<String> buttons = Arrays.asList(
                        "Злітати на тестовий рейд по чотирьох позиціях (+15 монет)",
                        "Відвезти гуманітарку на передок (+15 монет)",
                        "Знайти зрадника та здати в СБУ (+15 монет)",
                        "Навести арту на орків (+15 монет)",
                        "Притягнути танк трактором (+15 монет)"
                );
                buttons = getRandom3(buttons);

                // send buttons for level 4
                attachButtons(message, Map.of(
                        "Купити Джавелін (50 монет)", "level_4_task"
                ));
                sendApiMethodAsync(message);
            }

            // End Level 4
            if (update.getCallbackQuery().getData().equals("level_4_task") && getLevel(chartId) == 4) {
                // increase level
                setLevel(chartId, 5);

                // send message for level 5
                message = createMessage("*Джавелін твій. Повний вперед!*");
                message.setChatId(chartId);

                // send image for level 5
                sendImage("final", chartId);

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

    public void sendImage(String name, Long chatId) {
        SendAnimation animation = new SendAnimation();

        InputFile inputFile = new InputFile();
        inputFile.setMedia(new File("images/" + name + ".gif"));

        animation.setAnimation(inputFile);
        animation.setChatId(chatId);

        executeAsync(animation);
    }

    public int getLevel(Long chatId) {
        return levels.getOrDefault(chatId, 1);
    }

    public void setLevel(Long chatId, int level){
        levels.put(chatId, level);
    }

    public List<String> getRandom3(List<String> variants) {
        ArrayList<String> copy = new ArrayList<>(variants);
        Collections.shuffle(copy);
        return copy.subList(0, 3);
    }
}