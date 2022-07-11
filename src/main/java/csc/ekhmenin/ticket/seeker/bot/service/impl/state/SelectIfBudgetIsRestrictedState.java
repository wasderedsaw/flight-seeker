package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SelectIfBudgetIsRestrictedState implements State {
    @Override
    public State next(String userInput, Request request) {
        return "no".equals(userInput) ? new SelectIfDestinationIsKnownState() : new SelectBudgetState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(new ArrayList<>());
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Да");
        button1.setCallbackData("yes");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Нет");
        button2.setCallbackData("no");
        rows.get(0).add(button1);
        rows.get(0).add(button2);
        inlineKeyboardMarkup.setKeyboard(rows);
        message.setText("Ограничен ли Ваш бюджет?");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
