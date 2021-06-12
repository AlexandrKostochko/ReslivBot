package by.resliv.bot;

import by.resliv.service.KeyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Locale;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private KeyResponseService keyResponseService;

    @Value("${bot.name}")
    private String botUserName;

    @Value("${bot.token}")
    private String botToken;


    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        if ('/' == (text.toCharArray()[0])) {
            switch (text) {
                case "/start":
                    message.setText("Добро пожаловать, команда /help для помощи в навигации");
                    break;
                case "/help":
                    message.setText("Введите название города для получения интересных фактов о нём \n" +
                            "/start - для начала работы \n" +
                            "/help - для помощи в навигации \n" +
                            "/allCities - для того, чтобы узнать, какие города есть в списке");
                    break;
                case "/allCities":
                    message.setText(keyResponseService.keys());
                    break;
            }
        } else {
            message.setText(keyResponseService.response(text.toLowerCase(Locale.ROOT).trim()));
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

}
