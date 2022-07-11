package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SelectDateTypeState implements State {
    @Override
    public State next(String userInput, Request request) {
        request.setDateType(userInput);
        return "specific".equals(userInput) ? new SelectDepartureDateState() : new TicketSelectionState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(new ArrayList<>());
        rows.add(new ArrayList<>());
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Конкретные даты");
        button1.setCallbackData("specific");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("На какие-нибудь выходные");
        button2.setCallbackData("any-weekend");
        rows.get(0).add(button1);
        rows.get(1).add(button2);
        inlineKeyboardMarkup.setKeyboard(rows);
        message.setText("Когда вы планируете лететь?");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
