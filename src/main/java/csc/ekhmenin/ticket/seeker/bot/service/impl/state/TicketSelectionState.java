package csc.ekhmenin.ticket.seeker.bot.service.impl.state;

import csc.ekhmenin.ticket.seeker.bot.service.dto.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TicketSelectionState implements State {
    @Override
    public State next(String userInput, Request request) {
        System.out.println(request);
        return this;
    }

    @Override
    public SendMessage generateMessage(Request request) {
        SendMessage message = new SendMessage();
        message.setText("Сейчас что-нибудь подберу");
        return message;
    }
}
