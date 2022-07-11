package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class SelectFlightTypeState implements State {
    @Override
    public State next(String userInput, Request request) {
        request.setReturn(!"one-way".equals(userInput));
        return new SelectTravellerAmountState();
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(new ArrayList<>());
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("В одну сторону");
        button1.setCallbackData("one-way");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Туда-обратно");
        button2.setCallbackData("return");
        rows.get(0).add(button1);
        rows.get(0).add(button2);
        inlineKeyboardMarkup.setKeyboard(rows);
        message.setText("Выберите тип перелета");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
