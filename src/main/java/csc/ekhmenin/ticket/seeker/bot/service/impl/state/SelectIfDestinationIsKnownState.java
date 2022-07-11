package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SelectIfDestinationIsKnownState implements State {
    @Override
    public State next(String userInput, Request request) {
        if ("specific".equals(userInput)) {
            return new SelectSpecificDestinationState();
        } else {
            request.setTo(userInput);
            if (request.isReturn()) {
                return new SelectDateTypeState();
            }
            return new TicketSelectionState();
        }
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(new ArrayList<>());
        rows.add(new ArrayList<>());
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Конкретное место");
        button1.setCallbackData("specific");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("По России");
        button2.setCallbackData("russia");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("В страны Шенгена");
        button3.setCallbackData("schengen");
        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Куда угодно");
        button4.setCallbackData("whatever");
        rows.get(0).add(button1);
        rows.get(0).add(button2);
        rows.get(1).add(button3);
        rows.get(1).add(button4);
        inlineKeyboardMarkup.setKeyboard(rows);
        message.setText("Выберите место назначения");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
