package csc.ekhmenin.ticket.seeker.bot.service.impl;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import csc.ekhmenin.ticket.seeker.bot.service.exception.SessionNotFoundException;
import csc.ekhmenin.ticket.seeker.bot.service.impl.state.SelectDepartureCityState;
import csc.ekhmenin.ticket.seeker.bot.service.impl.state.State;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.util.Optional;

@Component
public class Bot extends TelegramLongPollingSessionBot {

    @Override
    public String getBotUsername() {
        return System.getenv("TELEGRAM_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("TELEGRAM_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update, Optional<Session> botSession) {
        final String chatId;
        if (update.hasMessage()) {
            chatId = String.valueOf(update.getMessage().getChatId());
        } else {
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }
        if (botSession.isEmpty()) {
            throw new SessionNotFoundException();
        }
        Session userSession = botSession.get();
        State state;
        if (update.hasMessage() && "/start".equals(update.getMessage().getText())) {
            userSession.setAttribute("request", new Request());
            state = new SelectDepartureCityState();
            userSession.setAttribute("state", state);
        } else {
            state = (State) userSession.getAttribute("state");
            String reply;
            if (update.hasCallbackQuery()) {
                reply = update.getCallbackQuery().getData();
            } else {
                reply = update.getMessage().getText();
            }
            userSession.setAttribute("state", state.next(reply, (Request) userSession.getAttribute("request")));
            state = state.next(reply, (Request) userSession.getAttribute("request"));
        }
        sendMessage(state.generateMessage((Request) userSession.getAttribute("request")), chatId);
    }

    private void sendMessage(SendMessage message, String chatId) {
        message.setChatId(chatId);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}